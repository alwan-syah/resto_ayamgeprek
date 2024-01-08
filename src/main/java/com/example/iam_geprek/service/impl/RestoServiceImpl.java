package com.example.iam_geprek.service.impl;

import com.example.iam_geprek.dto.request.RestoRequest;
import com.example.iam_geprek.dto.response.RestoResponse;
import com.example.iam_geprek.entity.Customer;
import com.example.iam_geprek.entity.Resto;
import com.example.iam_geprek.repository.RestoRepository;
import com.example.iam_geprek.service.RestoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class RestoServiceImpl implements RestoService {
    @PersistenceContext
    private EntityManager em;
    private final RestoRepository restoRepository;

    @Override
    public RestoResponse create(RestoRequest restoRequest) {
        String nativeQuery = "INSERT INTO m_resto (id,no_izin,name,address,mobile_phone,resto_code)" + "VALUES(?,?,?,?,?,?)";
        em.createNativeQuery(nativeQuery)
                .setParameter(1, UUID.randomUUID().toString())
                .setParameter(2, restoRequest.getNoIzin())
                .setParameter(3, restoRequest.getName())
                .setParameter(4, restoRequest.getAddress())
                .setParameter(5, restoRequest.getPhone())
                .setParameter(6, restoRequest.getRestoCode())
                .executeUpdate();

        Resto resto = restoRepository.findByRestoCode(restoRequest.getRestoCode());
        return RestoResponse.builder()
                .id(resto.getId())
                .nameRestaurant(resto.getName())
                .addressRestaurant(resto.getAddress())
                .addressRestaurant(restoRequest.getAddress())
                .phoneRestaurant(restoRequest.getPhone())
                .build();
    }

    @Override
    public RestoResponse getById(String id) {
        String nativeQuery = "SELECT * FROM m_resto WHERE id=?";
        Query query = em.createNativeQuery(nativeQuery)
                .setParameter(1, id);
        List<Object[]> results = query.getResultList();
        Object[] resto = results.get(0);
        if (resto != null) {
            return RestoResponse.builder()
                    .id((String) resto[0])
                    .noIzinRestaurant((String) resto[1])
                    .nameRestaurant((String) resto[2])
                    .addressRestaurant((String) resto[3])
                    .phoneRestaurant((String) resto[4])
                    .build();
        }
        return null;
    }

    @Override
    public List<RestoResponse> getAll() {
        String nativeQuery = "SELECT id, no_izin, name, address, mobile_phone,resto_code FROM m_resto";
        Query query = em.createNativeQuery(nativeQuery);
        List<Object[]> results = query.getResultList();
        List<RestoResponse> restoResponses = results.stream()
                .map(resto -> RestoResponse.builder()
                        .id((String) resto[0])
                        .noIzinRestaurant((String) resto[1])
                        .nameRestaurant((String) resto[2])
                        .addressRestaurant((String) resto[3])
                        .phoneRestaurant((String) resto[4])
                        .restoCode((String) resto[5])
                        .build())
                .collect(Collectors.toList());
        return restoResponses;
    }

    @Override
    public RestoResponse update(RestoRequest restoRequest) {
        Query findRestoQuery = em.createNativeQuery("SELECT * FROM m_resto WHERE id = ?", Resto.class)
                .setParameter(1, restoRequest.getId());
        List<Resto> resultList = findRestoQuery.getResultList();
        if (!resultList.isEmpty()) {
            Query queryUpdate = em.createNativeQuery("UPDATE m_resto SET  no_izin=?,name=?,address=?, mobile_phone=?, resto_code =? WHERE id=?")
                    .setParameter(1, restoRequest.getNoIzin())
                    .setParameter(2, restoRequest.getName())
                    .setParameter(3, restoRequest.getAddress())
                    .setParameter(4, restoRequest.getPhone())
                    .setParameter(5, restoRequest.getRestoCode())
                    .setParameter(6, restoRequest.getId());
            queryUpdate.executeUpdate();

            return RestoResponse.builder()
                    .id(restoRequest.getId())
                    .noIzinRestaurant(restoRequest.getNoIzin())
                    .nameRestaurant(restoRequest.getName())
                    .addressRestaurant(restoRequest.getAddress())
                    .phoneRestaurant(restoRequest.getPhone())
                    .restoCode(restoRequest.getRestoCode())
                    .build();
        }
        return null;
    }


    @Override
    public void delete(String id) {
        try {
            Query nativeQuery = em.createNativeQuery("DELETE FROM m_resto WHERE id=?")
                    .setParameter(1, id);
            int resto = nativeQuery.executeUpdate();
            if (resto > 0) {
                restoRepository.deleteById(id);
                System.out.println("Delete Success");
            } else {
                System.out.println("id not found");
            }
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
    }
}
