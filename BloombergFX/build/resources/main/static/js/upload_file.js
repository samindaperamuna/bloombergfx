document.getElementById("file-upload").onchange = function() {
	document.getElementById("file-path").innerHTML = this.files[0].name;
};