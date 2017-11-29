function isValid() {
	if (document.getElementById("file-upload").files.length == 0) {
		alert("You can't upload an empty file.");

		return false;
	}

	return true;
}

document.getElementById("file-upload").onchange = function() {
	document.getElementById("file-path").innerHTML = this.files[0].name;
};

document.getElementById("form").onsubmit = function() {
	return isValid();
};