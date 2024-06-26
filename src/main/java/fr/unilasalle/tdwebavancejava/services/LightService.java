package fr.unilasalle.tdwebavancejava.services;
import fr.unilasalle.tdwebavancejava.exceptions.DBException;
import fr.unilasalle.tdwebavancejava.exceptions.NotFoundException;
import fr.unilasalle.tdwebavancejava.models.Light;
import fr.unilasalle.tdwebavancejava.repositories.LightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LightService {

    private final LightRepository lightRepository;

    /**
     * Récupérer toutes les lampes
     * @return
     */
    public List<Light> getAllLights() {
        return this.lightRepository.findAll();
    }

    /**
     * Créer ou mettre à jour une lampe
     * @param light
     * @return
     * @throws NotFoundException
     * @throws DBException
     */
    public Light updateLight(Light light) throws NotFoundException,DBException {

        Light existingLight = null;

        if (light.getId() != null){
            // Modifier une lampe existante
            existingLight = this.lightRepository.findById(light.getId()).orElse(null);
            if (existingLight == null) {
                throw new NotFoundException("Could not find light with id " + light.getId());
            }
        }else{
            existingLight = new Light();
        }

        existingLight.setTitle(light.getTitle());
        existingLight.setToggle(light.getToggle());
        existingLight.setColor(light.getColor());

        try {
            Light lightUpdated = this.lightRepository.save(existingLight);
            return lightUpdated;
        } catch (DBException e) {
            throw new DBException("Error while updating light");
        }
    }

    /**
     * Supprimer une lampe
     * @param id
     * @throws NotFoundException
     * @throws DBException
     */

    public void deleteLight(Long id) throws NotFoundException, DBException {
        Light existingLight = this.lightRepository.findById(id).orElse(null);
        if (existingLight == null) {
            throw new NotFoundException("Could not find light with id " + id);
        }
        try {
            this.lightRepository.delete(existingLight);
        } catch (DBException e) {
            throw new DBException("Error while deleting light with id " + id);
        }
    }
}
