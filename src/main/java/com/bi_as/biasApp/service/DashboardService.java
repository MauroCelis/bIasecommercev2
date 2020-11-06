package com.bi_as.biasApp.service;

import com.bi_as.biasApp.dao.DashboardRepository;
import com.bi_as.biasApp.domain.Dashboard;
import com.bi_as.biasApp.domain.Persona;
import com.bi_as.biasApp.domain.User;
import com.bi_as.biasApp.dto.DashboardDto;
import com.bi_as.biasApp.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {
    DashboardRepository dashboardRepository;
    UserService userService;
    PersonaService personaService;

    @Autowired
    public DashboardService(DashboardRepository dashboardRepository,UserService userService,PersonaService personaService) {
        this.dashboardRepository = dashboardRepository;
        this.userService=userService;
        this.personaService=personaService;
    }

    public List<DashboardDto> findAllDashboard(){
        List<DashboardDto> dashboardDtoList=new ArrayList<>();
        for(Dashboard dashboard:dashboardRepository.findAll()){
            dashboardDtoList.add(new DashboardDto(dashboard));
        }
        return dashboardDtoList;
    }

    public DashboardDto addDashboard(DashboardDto dashboardDto) {
       Persona user=personaService.findPersonaId(/*dashboardDto.getIdUser()*/1);
        Dashboard dashboard=new Dashboard();
        //dashboard.setIdDashboar(dashboardDto.getIdDashboard());
        dashboard.setName(dashboardDto.getName());
        dashboard.setActive(dashboardDto.getActive());
        dashboard.setTxUser(dashboardDto.getTxUser());
        dashboard.setTxHost(dashboardDto.getTxHost());
        dashboard.setTxDate(dashboardDto.getTxDate());
        dashboard.setUserIdUser(user);
        dashboardRepository.save(dashboard);
        return dashboardDto;
    }


    public String deleteDashboard(DashboardDto dashboardDto) {
        //   Persona user=userService.getUserByid(dashboardDto.getIdUser());
        Dashboard dashboard=dashboardRepository.findByIdDashboard(dashboardDto.getIdDashboard());

        System.out.println();
        System.out.println(dashboard);
        System.out.println();

        dashboard.setActive(0);
//        dashboard.setUserIdUser(user);
        dashboardRepository.save(dashboard);
        return "se elimino el dash";
    }

    public String editDashboard(DashboardDto dashboardDto) {
        //   Persona user=userService.getUserByid(dashboardDto.getIdUser());
        Dashboard dashboard=dashboardRepository.findByIdDashboard(dashboardDto.getIdDashboard());

        System.out.println();
        System.out.println(dashboard);
        System.out.println();

        dashboard.setName(dashboardDto.getName());
//        dashboard.setUserIdUser(user);
        dashboardRepository.save(dashboard);
        return "se edito el dash";
    }
    public Dashboard getDashboardByIdDashboard(int idDashboard){
        Dashboard dashboard=dashboardRepository.findByIdDashboard(idDashboard);
        return dashboard;
    }

    /*public List<DashboardDto> findDashboardListByUserIdWithDashboardDtoParameter(int idUser){

        return findDashboardListByUserId(idUser);
    }*/

    public List<DashboardDto> findDashboardListByUserId(int idUser){
        List<DashboardDto> dashboardDtoList=new ArrayList<>();
        //User user=userService.getUserByid(idUser);
        Persona persona=personaService.findPersonaId(idUser);
        for(Dashboard dashboard:persona.getDashboardList()){
            if(dashboard.getActive()==1){
                dashboardDtoList.add(new DashboardDto(dashboard));
            }
        }
        return dashboardDtoList;
    }
}
