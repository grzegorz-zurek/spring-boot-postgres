import os
import random
import datetime
from faker import Faker
from sqlalchemy import create_engine, Column, Integer, String, ForeignKey, TIMESTAMP
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker, relationship


DB_STRING = os.environ.get("RDS_DB_STRING")
if not DB_STRING:
    raise ValueError("RDS_DB_STRING was not found in the environment.")


Base = declarative_base()


class Customer(Base):
    __tablename__ = "customers"
    id = Column(Integer, primary_key=True)
    first_name = Column(String)
    last_name = Column(String)
    email_address = Column(String)
    bookings = relationship("Booking", back_populates="customer")


class Room(Base):
    __tablename__ = "rooms"
    id = Column(Integer, primary_key=True)
    number = Column(String)
    size = Column(Integer)
    price = Column(Integer)
    bookings = relationship("Booking", back_populates="room")


class Booking(Base):
    __tablename__ = "bookings"
    id = Column(Integer, primary_key=True)
    from_date = Column(TIMESTAMP)
    to_date = Column(TIMESTAMP)
    price = Column(Integer)
    customer_id = Column(Integer, ForeignKey("customers.id"))
    room_id = Column(Integer, ForeignKey("rooms.id"))
    customer = relationship("Customer", back_populates="bookings")
    room = relationship("Room", back_populates="bookings")


engine = create_engine(DB_STRING)
Base.metadata.create_all(engine)

Session = sessionmaker(bind=engine)
session = Session()
faker = Faker()


def insert_data(num_customers, num_rooms, num_bookings):
    for _ in range(num_customers):
        customer = Customer(
            first_name=faker.first_name(),
            last_name=faker.last_name(),
            email_address=faker.email(),
        )
        session.add(customer)

    for j in range(num_rooms):
        room = Room(
            number=f"{100+j}", size=random.randint(1, 5), price=random.randint(50, 200)
        )
        session.add(room)

    session.commit()

    customers = session.query(Customer).all()
    rooms = session.query(Room).all()

    for _ in range(num_bookings):
        customer = random.choice(customers)
        room = random.choice(rooms)
        from_date = datetime.datetime.now() + datetime.timedelta(
            days=random.randint(11, 100)
        )
        to_date = from_date + datetime.timedelta(days=random.randint(4, 15))

        existing_customer_booking = (
            session.query(Booking).filter_by(customer_id=customer.id).first()
        )
        if existing_customer_booking:
            continue

        overlapping_bookings = (
            session.query(Booking)
            .filter(
                Booking.room_id == room.id,
                Booking.to_date > from_date,
                Booking.from_date < to_date,
            )
            .first()
        )

        if not overlapping_bookings:
            num_days = (to_date - from_date).days
            total_price = num_days * room.price

            booking = Booking(
                from_date=from_date,
                to_date=to_date,
                price=total_price,
                customer_id=customer.id,
                room_id=room.id,
            )
            session.add(booking)

    session.commit()


insert_data(num_customers=50, num_rooms=25, num_bookings=30)

session.close()
