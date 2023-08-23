package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.dto_request.WuXingRequestDTO;
import finalproject.EcommerceApp.dto_response.WuXingResponseDTO;
import finalproject.EcommerceApp.factory.WuXingFactory;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.model.WuXing;
import finalproject.EcommerceApp.repository.AbstractBaseRepository;
import finalproject.EcommerceApp.repository.WuXingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class WuXingService extends AbstractBaseService<WuXing, Long> {
    @Autowired
    private WuXingFactory wuXingFactory;

    private final WuXingRepository repository;

    public WuXingService(WuXingRepository repository) {
        super(repository);
        this.repository = repository;
    }


    public WuXing findBySystemUser(SystemUser systemUser) {
        return repository.findBySystemUser(systemUser);
    }

    public WuXingResponseDTO updateWuXing(WuXingRequestDTO requestDTO, SystemUser systemUser) {
        WuXing wuXing = findBySystemUser(systemUser);
        wuXing.setBirthTime(requestDTO.getBirthTime());
        wuXing.setBirthDay(requestDTO.getBirthDay());
        wuXing.setBirthDay(requestDTO.getBirthDay());
        wuXing.setBirthMonth(requestDTO.getBirthMonth());
        wuXing.setBirthYear(requestDTO.getBirthYear());
        repository.save(wuXing);
        return wuXingFactory.toResponseDTO(wuXing);
    }
}
