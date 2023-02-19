package ul.info.digitalwallet.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ul.info.digitalwallet.common.exceptions.ProfileNotFoundException;
import ul.info.digitalwallet.common.models.Profile;
import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.repository.ProfileRepository;
import ul.info.digitalwallet.common.service.ProfileService;
import ul.info.digitalwallet.common.service.dto.ProfileDTO;
import ul.info.digitalwallet.common.service.mapper.ProfileMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Profile}.
 */
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final Logger log = LoggerFactory.getLogger(ProfileServiceImpl.class);

    private final ProfileRepository profileRepository;

    private final ProfileMapper profileMapper;

    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    public void save(ProfileDTO profileDTO, User user) {
        log.debug("Request to save Profile {} of user {}", profileDTO, user);
        Profile profile = profileMapper.toEntity(profileDTO);
        profile.setUser(user);
        profileRepository.save(profile);
    }

    @Override
    public ProfileDTO update(ProfileDTO profileDTO) {
        log.debug("Request to update Profile : {}", profileDTO);
        Profile profile = profileMapper.toEntity(profileDTO);
        profile = profileRepository.save(profile);
        return profileMapper.toDto(profile);
    }

    @Override
    public Optional<ProfileDTO> partialUpdate(ProfileDTO profileDTO) {
        log.debug("Request to partially update Profile : {}", profileDTO);

        return profileRepository
            .findById(profileDTO.getId())
            .map(existingProfile -> {
                profileMapper.partialUpdate(existingProfile, profileDTO);

                return existingProfile;
            })
            .map(profileRepository::save)
            .map(profileMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfileDTO> findAll() {
        log.debug("Request to get all Profiles");
        return profileRepository.findAll().stream().map(profileMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProfileDTO> findOne(Long id) {
        log.debug("Request to get Profile : {}", id);
        return profileRepository.findById(id).map(profileMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Profile : {}", id);
        profileRepository.deleteById(id);
    }

    @Override
    public ProfileDTO findByUser(User user) {
        log.debug("Request to get Profile of user: {}", user.getUsername());
        return profileRepository.findByUser(user).map(profileMapper::toDto).orElseThrow(() -> new ProfileNotFoundException(user.getUsername()));
    }
}
