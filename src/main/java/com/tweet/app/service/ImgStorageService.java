package com.tweet.app.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tweet.app.exception.ApplicationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImgStorageService {

	public void storage(MultipartFile file, String fileName) throws ApplicationException {
		try {
			if (!isImageFileByImageIO(file)) {
				throw new ApplicationException("画像ファイルではありません");
			}
			Path filePath = Paths.get(fileName);
			// アップロードファイルをバイト値に変換
			byte[] bytes = file.getBytes();

			// バイト値を書き込む為のファイルを作成して指定したパスに格納
			OutputStream stream = Files.newOutputStream(filePath);

			// ファイルに書き込み
			stream.write(bytes);

		} catch (IOException e) {
			throw new ApplicationException("アップロードしたファイルに問題があります");
		}
	}

	// ファイルが画像なのか判定
	private boolean isImageFileByImageIO(MultipartFile file) throws IOException {
		// MultipartFileをFile形式に変換
		String tmpFilename = UUID.randomUUID().toString();
		File convFile = new File(tmpFilename);

		try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();

			// ImageIOで、ファイルを読み込み
			if (convFile != null && convFile.isFile()) {
				BufferedImage bi = ImageIO.read(convFile);

				// 引数に渡したFileが画像ファイル以外の場合、BufferedImageがnullで返ってくる。
				if (bi != null) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (IOException e) {
			// エラーはそのままスローする
			throw e;
		} finally {
			// 一時ファイルの削除
			convFile.delete();
		}

	}

	public void deleteBeforeImgFile(String userId, String nowImgFileName) {
		File dir = new File("img/icon");

		// listFilesメソッドを使用して一覧を取得する
		File[] fileArray = dir.listFiles();

		// ファイルの削除
		Arrays.stream(fileArray)
				.filter(file -> !file.getName().equals(nowImgFileName) && file.getName().startsWith(userId + "."))
				.forEach(File::delete);
	}
}
