//package com.space.service.impl;
//
//import com.space.model.Ship;
//import com.space.repository.ShipRepository;
//import com.space.service.ShipService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ShipServiceImpl implements ShipService {
//
//    @Autowired
//    private ShipRepository shipRepository;
//
//
//    @Override
//    public Ship addShip(Ship ship) {
//        return shipRepository.saveAndFlush(ship);
//    }
//
//    @Override
//    public void delete(long id) {
//        shipRepository.delete(id);
//    }
//
//    @Override
//    public Ship getByName(String name) {
//        return shipRepository.findByName(name);
//    }
//
//    @Override
//    public Ship editShip(Ship ship) {
//        return shipRepository.saveAndFlush(ship);
//    }
//
//    @Override
//    public List<Ship> getAll() {
//        return shipRepository.findAll();
//    }
//}
