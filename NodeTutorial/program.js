var book = require("./lib/grade").book;

var express = require("express");

var app = express();

app.get("/", function(req,res){
	res.send("Hello World!!!");
});

app.get("/grade", function(req,res){
	var grades = req.query.grades.split(",");
	for(var i=0;i<grades.length;i++){
		book.addGrade(parseInt(grades[i]));
	}
	var average = book.average();
	res.send("Your average: "+ average);
});
app.listen(3000);
console.log("Connecting.....");