package ul.info.digitalwallet.service;

import ul.info.digitalwallet.models.Profile;
import ul.info.digitalwallet.service.dto.ProfileDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Profile}.
 */
public interface ProfileService {
    /**
     * Save a profile.
     *
     * @param profileDTO the entity to save.
     * @return the persisted entity.
     */
    ProfileDTO save(ProfileDTO profileDTO);

    /**
     * Updates a profile.
     *
     * @param profileDTO the entity to update.
     * @return the persisted entity.
     */
    ProfileDTO update(ProfileDTO profileDTO);

    /**
     * Partially updates a profile.
     *
     * @param profileDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProfileDTO> partialUpdate(ProfileDTO profileDTO);

    /**
     * Get all the profiles.
     *
     * @return the list of entities.
     */
    List<ProfileDTO> findAll();

    /**
     * Get the "id" profile.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProfileDTO> findOne(Long id);

    /**
     * Delete the "id" profile.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
