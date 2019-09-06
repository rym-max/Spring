package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.entity.ERMS.Region;
import com.tongji.bwm.repository.ERMS.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService implements IRegionService<Integer> {

    @Autowired
    private RegionRepository regionRepository;
    
    @Override
    public Integer Insert(Region region) {
        return regionRepository.save(region).getId();
    }

    @Override
    public Region GetById(Integer id) {
        Optional<Region> optional = regionRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public void Update(Region region) {
        regionRepository.save(region);
    }

    @Override
    public void Delete(Region region) {
        regionRepository.delete(region);
    }

    @Override
    public void Delete(Integer id) {
        regionRepository.deleteById(id);
    }

    @Override
    public List<Region> GetAll() {
        return regionRepository.findAll();
    }

    @Override
    public Region GetByName(String name) {
        return regionRepository.findByName(name);
    }

    @Override
    public List<Region> findByParentId(Integer parentId) {
        return regionRepository.findByParentId(parentId);
    }
}
