class UrlMappings {
  static mappings = {
    "/v1/status(.$format)?"(controller: "status", action: "index")
    "500"(controller: "error", action:"error500")
    "400"(controller: "error", action:"error400")
    "401"(controller: "error", action:"error401")
    "402"(controller: "error", action:"error402")
    "403"(controller: "error", action:"error403")
    "404"(controller: "error", action:"error404")
    "405"(controller: "error", action:"error405")
  }
}
