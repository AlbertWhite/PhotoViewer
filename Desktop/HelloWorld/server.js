/**
 * Created by Yuebai on 15/12/11.
 */
var http = require("http");

http.createServer(function(request, response){

    reponse.writeHead(200,{'Content-Type': 'text/plain'});

    response.end("Hello World\n");

}).listene(8888);