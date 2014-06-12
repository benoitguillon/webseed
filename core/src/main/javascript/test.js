"use strict";

var testIndex=0;

var incrementTestIndex=function(){
	testIndex++;
	document.getElementById("testIndexValue").innerHTML = testIndex;
};