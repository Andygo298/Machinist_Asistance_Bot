package com.andygo298.machinist_asistant_bot.service;

import com.andygo298.machinist_asistant_bot.model.UserProfileData;
import com.andygo298.machinist_asistant_bot.repository.UserProfileMongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersProfileDataService {

    private UserProfileMongoRepository profileMongoRepository;

    public UsersProfileDataService(UserProfileMongoRepository profileMongoRepository) {
        this.profileMongoRepository = profileMongoRepository;
    }

    public List<UserProfileData> getAllProfiles() {
        return profileMongoRepository.findAll();
    }

    public void saveUserProfileData(UserProfileData userProfileData) {
        profileMongoRepository.save(userProfileData);
    }

    public void deleteUsersProfileData(String profileDataId) {
        profileMongoRepository.deleteById(profileDataId);
    }

    public UserProfileData getUserProfileData(long chatId) {
        return profileMongoRepository.findByChatId(chatId);
    }

}
