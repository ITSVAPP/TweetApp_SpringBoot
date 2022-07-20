let attachments = [];

// イベントの確認
addEventListener("trix-attachment-add", function(event) {
	// 添付ファイルや画像を追加するとこのイベントが実行される
	// https://trix-editor.org/js/attachments.js
	console.log('trix-attachment-add');
	attachments.push(event.attachment);
});

addEventListener("trix-attachment-remove", function(event) {
	// 添付ファイルや画像を削除するとこのイベントが実行される
	console.log('trix-attachment-remove');
	attachments = attachments.filter(data => data.id != event.attachment.id);
	console.log(attachments);
});


const postData = async () => {
	const form = new FormData();
	attachments.forEach(o => {
		form.append('files', o.file, o.file.name);
	})
	form.append('tweet', document.getElementById("tweetInput").value);

	const xsrf = Cookies.get('XSRF-TOKEN');

	try {
		const response = await fetch("/tweetForm", {
			method: "POST",   // GET POST PUT DELETEなど
			headers: {
				'X-XSRF-TOKEN': xsrf
			},
			body: form    // リクエスト本文をセット
		});

		const data = await response;
		location.href = data.url;

	} catch (e) {
		console.error("ネットワークエラー", e)
	}
}

document.getElementById("tweetbtn").addEventListener("click", postData);