var gradebook = {
	_grades: [],
	addGrade: function(newGrade){
		this._grades.push(newGrade);
	},
	getCountOfGrade: function(){
		return this._grades.length;
	},
	average: function(){
		var total = 0;
		for(var i=0; i< this._grades.length;i++){
			total += this._grades[i];
		}
		return total / this._grades.length;
	},
	reset: function(){
		this._grades = [];
	}
};

exports.book = gradebook;