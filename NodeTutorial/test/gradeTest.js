var book = require("../lib/grade").book;

exports["setUp"] = function(callback){
	book.reset();
	callback();
};

exports["can average grade"] = function(test){
	book.addGrade(100);
	book.addGrade(50);
	var avg = book.average();
	test.equal(avg, 75);
	test.done();
};

exports["can add new grade"] = function(test){
	book.addGrade(90);
	var count = book.getCountOfGrade();
	test.equal(count,1);
	test.done();
};

