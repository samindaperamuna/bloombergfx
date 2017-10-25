function isValid() {
	if (document.getElementById("file-name").value == "") {
		alert("We can't search for an empty file name.");

		return false;
	}

	return true;
}

document.getElementById("form").onsubmit = function() {
	return isValid();
};