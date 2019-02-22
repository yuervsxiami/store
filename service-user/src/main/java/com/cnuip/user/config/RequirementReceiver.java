package com.cnuip.user.config;

import com.cnuip.base.domain.user.Requirement;
import com.cnuip.user.service.RequirementService;
import com.cnuip.user.vo.RequirementProfessor;
import com.cnuip.user.vo.RequirementVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class RequirementReceiver {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RequirementService requirementService;

    @RabbitHandler
    @RabbitListener(queues = "professor.message")
    public void process(@Payload String entity) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RequirementVo requirementVo = objectMapper.readValue(entity, RequirementVo.class);
            if (requirementVo.getProfessors() != null) {
                for (RequirementProfessor professor : requirementVo.getProfessors()) {
                    Requirement requirement = new Requirement();
                    requirement.setRequirementId(professor.getOriginId());
                    requirement.setUserId(Long.valueOf(professor.getProfessorRemoteKey()));
                    requirement.setContent(requirementVo.getEnterpriseRequire().getRequirement());
                    requirementService.insert(requirementService.check(requirement));
                }
            }
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
        }
    }
}

