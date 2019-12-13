# PracticeDemo
window.onload = function(){
	var result = "'download': 'http://1255516392.vod2.myqcloud.com/390db1c9vodcq1255516392/b9581ec35285890796458437389/f0.mp4','preview': 'http://1255516392.vod2.myqcloud.com/390db1c9vodcq1255516392/b9581ec35285890796458437389/f0.mp4'";
	var downloadStart = result.indexOf("download") + 11;
	var downloadEnd = result.indexOf("preview") - 3;
	var download = result.substring(downloadStart, downloadEnd);
	console.log(download);
}
