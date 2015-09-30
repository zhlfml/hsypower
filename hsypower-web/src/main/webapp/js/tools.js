/**
 * read file, the browser need to support HTML5
 * 
 * @param input the file input control
 * @param callback the callback after read file
 */
function readURL(input, callback) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		if (typeof callback === "function") {
			reader.onload = callback;
		}
		reader.readAsDataURL(input.files[0]);
	}
}

/**
 * Preview an image before it is uploaded
 * 
 * @param input the file input control
 * @param img the image DOM object
 */
function previewImg(input, img) {
	var file = input.files[0];
	if (!file.type.startsWith("image")) {
		alert("请确保文件为图像类型");
		return false;
	}
	readURL(input, function(e) {
		$(img).attr("src", e.target.result);
	})
}