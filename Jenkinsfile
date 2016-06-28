node {
   
   stage 'Checkout'
   git url: 'https://github.com/cbos/cucumber-and-pipeline'

   stage 'Build'
   // Run the maven build and unit tests
   sh "mvn clean install"
   
   def newImage = docker.build "cbos/cucumber-and-pipeline2:${env.BUILD_TAG}"
   
   stage 'Unit test results'
   
   publishHTML(target:[allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'target/cucumber/', reportFiles: 'index.html', reportName: 'Cucumber unit tests'])
   
   stage 'Integration test'
   // Run the maven build
   
   newImage.withRun('-p 8082 -e "db.user=sa" -e "db.password=" -e "db.driverclass=org.hsqldb.jdbcDriver" -e "db.hibernate.dialect=org.hibernate.dialect.HSQLDialect" -e "db.jdbcurl=jdbc:hsqldb:mem:testdb"') {applicationContainer ->
       sh "sleep 10"
       sh "mvn install -Pintegrationtest -Dtarget.url=http://localhost:${hostPort(applicationContainer)}"
    }
  
   stage 'Integration test results'
   
   publishHTML(target:[allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'target/cucumberIT/', reportFiles: 'index.html', reportName: 'Cucumber integration tests'])
   
   step([$class: 'CucumberReportPublisher', fileExcludePattern: '', fileIncludePattern: 'cucumber*/*.json', ignoreFailedTests: false, jenkinsBasePath: '', jsonReportDirectory: 'target/', missingFails: false, parallelTesting: false, pendingFails: false, skippedFails: false, undefinedFails: false])
   
   stage 'Continue?'
   
   input message: 'Doorgaan naar productie?', ok: 'Gaan!'
   
   stage 'Deploy to production'
   
   echo "Deploy application to production"
}

def hostPort(container) {
    sh "docker inspect --format='{{(index (index .NetworkSettings.Ports \"8082/tcp\") 0).HostPort}}' ${container.id} > hostPort"
    readFile('hostPort').trim()
}