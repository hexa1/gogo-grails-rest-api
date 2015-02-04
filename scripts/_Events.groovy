// https://ant.apache.org/manual/Tasks/propertyfile.html

eventCompileStart = { msg ->
  def buildNumber = ("git rev-parse HEAD".execute().text?.trim())
  ant.propertyfile(
    file:"application.properties") {
    entry(key:"app.buildId", type:"string", value:buildNumber)
    entry(key:"app.buildDate", type:"date", value:new Date())
  }
}
