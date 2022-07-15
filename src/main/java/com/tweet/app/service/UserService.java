package com.tweet.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tweet.app.entity.UserData;
import com.tweet.app.exception.ApplicationException;
import com.tweet.app.form.IconChangeForm;
import com.tweet.app.form.UserDataForm;
import com.tweet.app.form.UserUpdateForm;
import com.tweet.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー業務ロジッククラス
 *
 */
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncorder;
	private final ImgStorageService imgStorageService;

	@Value("${error.duplicatekeyerr}")
	private String duplicatekeyErrMsg;

	@Value("${error.nouser}")
	private String noUserErrMsg;

	/**
	 * 全検索
	 */
	public List<UserData> findAll() {
		return userRepository.findAll();
	}

	/**
	 * ユーザー作成
	 * 
	 * @param userId
	 * @param name
	 * @param passowrd
	 * @param roll
	 * @throws ApplicationException
	 */
	public void createUser(UserDataForm form) throws ApplicationException {

		try {
			userRepository.insert(form.getUserId(), form.getName(), passwordEncorder.encode(form.getPassword()),
					form.getRole());
		} catch (DuplicateKeyException e) {
			throw new ApplicationException(duplicatekeyErrMsg);
		}
	}

	/**
	 * ユーザーIdによる検索
	 * 
	 * @param userId
	 * @return
	 * @throws ApplicationException
	 */
	public UserData findByUserId(String userId) throws ApplicationException {
		UserData userData = userRepository.findByUserId(userId);

		if (userData == null) {
			throw new ApplicationException(noUserErrMsg);
		}

		return userData;
	}

	/**
	 * ユーザー更新
	 * 
	 * @param userId
	 * @param name
	 * @param role
	 */
	public void updateUser(UserUpdateForm form) {
		userRepository.update(form.getUserId(), form.getName(), form.getRole());
	}

	/**
	 * ユーザー削除
	 * 
	 * @param userId
	 */
	public void deleteUser(String userId) {
		userRepository.delete(userId);
	}

	/**
	 * アイコン変更
	 * 
	 * @param form
	 * @param file
	 * @throws ApplicationException
	 */
	@Transactional
	public void changeUserIcon(IconChangeForm form, MultipartFile file) throws ApplicationException {
		String filePath = String.format("img/icon/%s.%s", form.getUserId(), form.getImageType());
		imgStorageService.storage(file, filePath);
		userRepository.changeIcon(form.getUserId(), "/" + filePath);
		imgStorageService.deleteBeforeImgFile(form.getUserId(), form.getUserId() + "." + form.getImageType());
	}

}
