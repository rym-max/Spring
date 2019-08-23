package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.entity.ERMS.Channel;
import com.tongji.bwm.repository.ERMS.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelService implements IChannelService<Integer> {

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public Integer Insert(Channel channel) {
        return channelRepository.save(channel).getId();
    }

    @Override
    public Channel GetById(Integer id) {
        Optional<Channel> optional = channelRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public Channel GetByCode(String code) {
        return channelRepository.findByCode(code);
    }

    @Override
    public void Update(Channel channel) {
        channelRepository.save(channel);
    }

    @Override
    public void Delete(Channel channel) {
        channelRepository.delete(channel);
    }

    @Override
    public void Delete(Integer id) {
        channelRepository.deleteById(id);
    }

    @Override
    public List<Channel> GetAll() {
        return channelRepository.findAll();
    }
}
