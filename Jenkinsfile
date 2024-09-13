pipeline {
    agent any

    environment {
        GIT_REPO_URL = 'https://github.com/piotr823/spring-boot-postgres.git'
        GITHUB_CREDENTIALS = credentials('spring-boot-postgres')
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    git branch: 'main', credentialsId: GITHUB_CREDENTIALS, url: GIT_REPO_URL
                }
            }
        }

        stage('Build and Run Tests') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'DATABASE_URL_ID', passwordVariable: 'DB_URL_PASSWORD', usernameVariable: 'DB_URL_USERNAME'),
                                     usernamePassword(credentialsId: 'USERNAME_ID', passwordVariable: 'USERNAME_PASSWORD', usernameVariable: 'USERNAME_USERNAME'),
                                     usernamePassword(credentialsId: 'PASSWORD_ID', passwordVariable: 'PASSWORD_PASSWORD', usernameVariable: 'PASSWORD_USERNAME'),]) {
                        env.database_url = DB_URL_PASSWORD
                        env.username = USERNAME_PASSWORD
                        env.password = PASSWORD_PASSWORD

                        bat './gradlew build'

                        bat 'set database_url ='
                        bat 'set username ='
                        bat 'set password ='
                    }
                }
            }
        }

        stage('Increment Version and Tag') {
            steps {
                script {
                    bat 'git config user.email "grzegorz.zurek@student.uj.edu.pl"'
                    bat 'git config user.name "Piotr823"'

                    bat 'git tag -a v1.0.0 -m "Version 1.0.0"'
                    bat 'git push --tags'
                }
            }
        }
    }
}