node('linux') {
  stage ('Poll') {
    checkout([
      $class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/yamlcppport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/yamlcppport.git'),
      string(name: 'PORT_DESCRIPTION', value: 'A C++ library for parsing and emitting YAML.'),
      string(name: 'BUILD_LINE', value: 'STABLE')
    ]
  }
}
