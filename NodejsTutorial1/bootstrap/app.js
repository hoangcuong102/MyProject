const express = require('express');
const path = require('path');
const chalk = require('chalk');
const morgan = require('morgan');
const debug = require('debug')('app');

var app = express();

app.use(morgan('tiny'));
app.use(express.static(path.join(__dirname, '/public')));

app.set('views', './src/views');
app.set('view engine', 'ejs');

app.get('/', function (req, res) {
    res.render('myejs');
});

app.listen(3000, function(){
    debug(`Connecting to ${chalk.green(3000)} port`);
});
