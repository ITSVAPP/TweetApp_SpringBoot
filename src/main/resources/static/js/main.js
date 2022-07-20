let cropper;

/**
* 丸くトリミング
*/
const getRoundedCanvas = (sourceCanvas) => {
	var canvas = document.createElement('canvas');
	var context = canvas.getContext('2d');
	var width = sourceCanvas.width;
	var height = sourceCanvas.height;

	canvas.width = width;
	canvas.height = height;
	context.imageSmoothingEnabled = true;
	context.drawImage(sourceCanvas, 0, 0, width, height);
	context.globalCompositeOperation = 'destination-in';
	context.beginPath();
	context.arc(width / 2, height / 2, Math.min(width, height) / 2, 0, 2 * Math.PI, true);
	context.fill();
	return canvas;
}

/*
リクエスト
*/
const changeIcon = async (blob) => {
	const userId = document.getElementById("userIdInput").value;
	const imageType = document.getElementById("triming_image").files[0].type.replace("image/", "");

	const trimedImageForm = new FormData();
	trimedImageForm.append('file', blob);
	trimedImageForm.append('userId', userId);
	trimedImageForm.append('imageType', imageType);

	const xsrf = Cookies.get('XSRF-TOKEN');

	try {
		const response = await fetch("/users/rest/changeIcon", {
			method: "POST",   // GET POST PUT DELETEなど
			headers: {
				'X-XSRF-TOKEN': xsrf
			},
			body: trimedImageForm    // リクエスト本文をセット
		});
		const data = await response;
		console.log(data);
	} catch (e) {
		console.error("ネットワークエラー", e)
	}
}

$(function() {
	$('#triming_image').on('change', function(event) {
		
		var trimingImage = event.target.files;

		// imageタグは1つしかファイルを送信できない仕組みと複数送信するパターンがある
		if (trimingImage.length > 1) {
			console.log(trimingImage.length + 'つのファイルが選択されました。');
			return false;
		}

		// 改め代入します。
		trimingImage = trimingImage[0];

		// 画像のチェック
		if (!trimingImage.type.match('image/jp.*') // jpg jpeg でない
			&& !trimingImage.type.match('image/png') // png でない
			&& !trimingImage.type.match('image/gif') // gif でない
			&& !trimingImage.type.match('image/bmp') // bmp でない
		) {
			alert('No Support ' + trimingImage.type + ' type image');
			$(this).val('');
			return false;
		}

		var fileReader = new FileReader();
		fileReader.onload = function(e) {
			var int32View = new Uint8Array(e.target.result);
			// see https://en.wikipedia.org/wiki/List_of_file_signatures
			if ((int32View.length > 4 && int32View[0] == 0xFF && int32View[1] == 0xD8 && int32View[2] == 0xFF && int32View[3] == 0xE0)
				|| (int32View.length > 4 && int32View[0] == 0xFF && int32View[1] == 0xD8 && int32View[2] == 0xFF && int32View[3] == 0xDB)
				|| (int32View.length > 4 && int32View[0] == 0xFF && int32View[1] == 0xD8 && int32View[2] == 0xFF && int32View[3] == 0xD1)
				|| (int32View.length > 4 && int32View[0] == 0x89 && int32View[1] == 0x50 && int32View[2] == 0x4E && int32View[3] == 0x47)
				|| (int32View.length > 4 && int32View[0] == 0x47 && int32View[1] == 0x49 && int32View[2] == 0x46 && int32View[3] == 0x38)
				|| (int32View.length = 2 && int32View[0] == 0x42 && int32View[1] == 0x4D && int32View[2] == 0x46 && int32View[3] == 0x38)
			) {
				// success
				$('#trimed_image').css('display', 'block');
				$('#trimed_image').attr('src', URL.createObjectURL(trimingImage));
				return true;
			} else {
				// failed
				alert('No Support ' + trimingImage.type + ' type image');
				$('#trimed_image').val('');
				return false;
			}
		};

		fileReader.readAsArrayBuffer(trimingImage);
		fileReader.onloadend = function(e) {
			var image = document.getElementById('trimed_image');
			var button = document.getElementById('crop_btn');

			var croppable = false;

			// 前データの破棄
			if (cropper) {
				cropper.destroy();
			}

			cropper = new Cropper(image, {
				aspectRatio: 1,
				viewMode: 1,
				ready: function() {
					croppable = true;
				},
			});

			// fileReaderが完了した後にボタンクリックイベントを作成する必要があります。
			button.onclick = function() {
				var croppedCanvas;

				if (!croppable) {
					alert('トリミングする画像が設定されていません。');
					return false;
				}

				croppedCanvas = cropper.getCroppedCanvas();
				// 下記toBlob関数はブラウザによって名前が異なる
				let blob;
				if (croppedCanvas.toBlob) {
					croppedCanvas.toBlob(function(blob) {
						changeIcon(blob);
					});
				} else if (croppedCanvas.msToBlob) {
					blob = croppedCanvas.msToBlob();
					changeIcon(blob);
				} else {
					imageURL = canvas.toDataURL();
				}

				// 画面にトリミング結果を出力
				const result = document.getElementById('result');
				let roundedImage;
				roundedCanvas = getRoundedCanvas(croppedCanvas);
				roundedImage = document.createElement('img');
				roundedImage.src = roundedCanvas.toDataURL()
				roundedImage.name = 'trimed';
				roundedImage.id = 'trimed';
				result.innerHTML = '';
				result.appendChild(roundedImage);
			};
		};
	});
});