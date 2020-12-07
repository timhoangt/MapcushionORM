package com.fdflib.example;

import com.fdflib.example.model.User;
//import com.fdflib.example.model.CarMake;
import com.fdflib.example.model.Tenant;
import com.fdflib.example.model.TenantUser;
import com.fdflib.example.model.Role;
import com.fdflib.example.model.UserRole;
import com.fdflib.example.model.Location;
import com.fdflib.example.model.Beacon;
import com.fdflib.example.model.Floor;
import com.fdflib.example.model.DeployedBeacon;
import com.fdflib.example.model.History;
import com.fdflib.example.model.State;
import com.fdflib.example.model.Queue;
import com.fdflib.example.model.Destination;
import com.fdflib.example.model.Device;
import com.fdflib.example.model.BeaconsRead;
import com.fdflib.example.model.DestinationQueue;
import com.fdflib.example.model.DestinationState;
import com.fdflib.example.model.TenantRole;
import com.fdflib.example.service.TenantService;
import com.fdflib.example.service.TenantRoleService;
import com.fdflib.example.service.UserService;
import com.fdflib.example.service.UserRoleService;
import com.fdflib.example.service.TenantUserService;
import com.fdflib.example.service.BeaconService;
import com.fdflib.example.service.LocationService;
import com.fdflib.example.service.FloorService;
import com.fdflib.example.service.DeployedBeaconService;
import com.fdflib.example.service.DestinationService;
import com.fdflib.example.service.DeviceService;
import com.fdflib.example.service.BeaconsReadService;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.persistence.database.DatabaseUtil;
import com.fdflib.service.FdfServices;
import com.fdflib.util.FdfSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian.gormanly on 10/9/15
 */
public class Mapcushion {
    public static void main(String[] args) {
        System.out.println("Hello 4DF World!"); // Display the string.

        // use the  settings within this method to customize the 4DFLib.  Note, everything in this method is optional.
        setOptionalSettings();

        // Create a array that will hold the classes that make up our 4df data model
        List<Class> myModel = new ArrayList<>();

        // Add our classes
        myModel.add(User.class);
        myModel.add(Tenant.class);
        myModel.add(TenantUser.class);
        myModel.add(Role.class);
        myModel.add(UserRole.class);
        myModel.add(Location.class);
        myModel.add(Beacon.class);
        myModel.add(Floor.class);
        myModel.add(DeployedBeacon.class);
        myModel.add(History.class);
        myModel.add(State.class);
        myModel.add(Queue.class);
        myModel.add(Destination.class);
        myModel.add(Device.class);
        myModel.add(BeaconsRead.class);
        myModel.add(DestinationQueue.class);
        myModel.add(DestinationState.class);
        myModel.add(TenantRole.class);

        // call the initialization of library!
        FdfServices.initializeFdfDataModel(myModel);

        // insert some demo data
        try {
            insertSomeData();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // do a few queries and output the results

    }

    /**
     * Everything set in this method is optional, but useful
     */
    private static void setOptionalSettings() {

        // get the 4dflib settings singleton
        FdfSettings fdfSettings = FdfSettings.getInstance();

        // set the database type and name and connection information
        // PostgreSQL settings
        //fdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.POSTGRES;
        //fdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_POSTGRES;

        // postgres default root user
        // root user settings are only required for initial database creation.  Once the database is created you
        // should remove this information
        //fdfSettings.DB_ROOT_USER = "postgres";

        // MySQL settings
        fdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.MYSQL;
        fdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_MYSQL;

        // MariaDB settings
        //fdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.MARIADB;
        //fdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_MARIADB;

        // MariaDB and MySQL default
        // root user settings are only required for initial database creation.  Once the database is created you
        // should remove this information
        fdfSettings.DB_ROOT_USER = "root";

        // root user password
        fdfSettings.DB_ROOT_PASSWORD = "";

        // Database encoding
        fdfSettings.DB_ENCODING = DatabaseUtil.DatabaseEncoding.UTF8;

        // Application Database name
        fdfSettings.DB_NAME = "mapcushion";

        // Database host
        fdfSettings.DB_HOST = "localhost";

        // Port is not required for DB defaults can be changed when needed
        // fdfSettings.DB_PORT = 3306;

        // Database user information
        fdfSettings.DB_USER = "mapcushion";
        fdfSettings.DB_PASSWORD = "mapcushionpass";

        // set the default system information
        fdfSettings.DEFAULT_SYSTEM_NAME = "Mapcushion Core API";
        fdfSettings.DEFAULT_SYSTEM_DESCRIPTION = "Central API service for the Mapcushion Application";

        // set the default tenant information
        fdfSettings.DEFAULT_TENANT_NAME = "Mapcushion";
        fdfSettings.DEFAULT_TENANT_DESRIPTION = "Main system Tenant";
        fdfSettings.DEFAULT_TENANT_IS_PRIMARY = true;
        fdfSettings.DEFAULT_TENANT_WEBSITE = "http://www.4dflib.com";

        // local dev, no ssl
        fdfSettings.USE_SSL = false;
        

    }

    private static void insertSomeData() throws InterruptedException {
        TenantService ts = new TenantService();
        UserService us = new UserService();
        TenantRoleService trs = new TenantRoleService();
        UserRoleService urs = new UserRoleService();
        TenantUserService tus = new TenantUserService();
        BeaconService bs = new BeaconService();
        LocationService ls = new LocationService();
        FloorService fs = new FloorService();
        DeployedBeaconService dbs = new DeployedBeaconService();
        DestinationService dests = new DestinationService();
        DeviceService devs = new DeviceService();
        BeaconsReadService brs = new BeaconsReadService();

        // create a couple tenants
        Tenant murray = new Tenant();
        murray.name = "Murray";
        ts.saveTenant(murray);

        Tenant doyle = new Tenant();
        doyle.name = "Doyle";
        ts.saveTenant(doyle);

        //give the tenants roles
        TenantRole murrayrole1 = new TenantRole();
        murrayrole1.tenantId = murray.id;
        murrayrole1.roleName = Role.Administrator;
        murrayrole1.isActive = true;
        trs.saveTenantRole(murrayrole1);

        TenantRole murrayrole2 = new TenantRole();
        murrayrole2.tenantId = murray.id;
        murrayrole2.roleName = Role.Teacher;
        murrayrole2.isActive = true;
        trs.saveTenantRole(murrayrole2);

        TenantRole murrayrole3 = new TenantRole();
        murrayrole3.tenantId = murray.id;
        murrayrole3.roleName = Role.Security;
        murrayrole3.isActive = true;
        trs.saveTenantRole(murrayrole3);

        TenantRole murrayrole4 = new TenantRole();
        murrayrole4.tenantId = murray.id;
        murrayrole4.roleName = Role.Parent;
        murrayrole4.isActive = true;
        trs.saveTenantRole(murrayrole4);

        TenantRole doylerole1 = new TenantRole();
        doylerole1.tenantId = doyle.id;
        doylerole1.roleName = Role.Administrator;
        doylerole1.isActive = true;
        trs.saveTenantRole(doylerole1);

        TenantRole doylerole2 = new TenantRole();
        doylerole2.tenantId = doyle.id;
        doylerole2.roleName = Role.Parent;
        doylerole2.isActive = true;
        trs.saveTenantRole(doylerole2);

        // create users 8 have id info, 12 do not
        User timhoang = new User();
        timhoang.firstName = "Tim";
        timhoang.lastName = "Hoang";
        timhoang.email = "timothy.hoang1@marist.edu";
        timhoang.password = "timspassword";
        timhoang.idType1 = "Passport";
        timhoang.addr1 = "35 Campus View Court";
        timhoang.city = "Poughkeepsie";
        timhoang.state = "New York";
        timhoang.zip = 12601;
        timhoang.country = "USA";
        timhoang.id1 = 123456789;
        timhoang.date1Init = "3/12/11";
        timhoang.date1Exp = "3/12/16";
        timhoang.dateBirth = "4/13/1998";
        timhoang.gender = "M";
        timhoang.eyeColor = "Brown";
        timhoang.height = 177.5f;
        timhoang.weight = 150.3f;
        us.saveUser(timhoang);
        
        User derinyetil = new User();
        derinyetil.firstName = "Derin";
        derinyetil.lastName = "Yetil";
        derinyetil.email = "derin.yetil1@marist.edu";
        derinyetil.password = "derinsspassword";
        derinyetil.idType1 = "Passport";
        derinyetil.addr1 = "35 Campus View Court";
        derinyetil.city = "Poughkeepsie";
        derinyetil.state = "New York";
        derinyetil.zip = 12601;
        derinyetil.country = "USA";
        derinyetil.id1 = 234567899;
        derinyetil.date1Init = "4/12/11";
        derinyetil.date1Exp = "4/12/16";
        derinyetil.dateBirth = "9/14/1999";
        derinyetil.gender = "M";
        derinyetil.eyeColor = "Brown";
        derinyetil.height = 170.5f;
        derinyetil.weight = 135.4f;
        us.saveUser(derinyetil);

        User austinkrakatoa = new User();
        austinkrakatoa.firstName = "Austin";
        austinkrakatoa.lastName = "Krakatoa";
        austinkrakatoa.email = "Akrak@gmail.com";
        austinkrakatoa.password = "oostensspassword";
        austinkrakatoa.idType1 = "Driver License";
        austinkrakatoa.addr1 = "141 Hang Dog Lane";
        austinkrakatoa.city = "Newington";
        austinkrakatoa.state = "Connecticut";
        austinkrakatoa.zip = 06111;
        austinkrakatoa.country = "USA";
        austinkrakatoa.id1 = 345678999;
        austinkrakatoa.date1Init = "3/12/12";
        austinkrakatoa.date1Exp = "3/12/17";
        austinkrakatoa.dateBirth = "2/13/1999";
        austinkrakatoa.gender = "M";
        austinkrakatoa.eyeColor = "Green";
        austinkrakatoa.height = 180.5f;
        austinkrakatoa.weight = 180.1f;
        us.saveUser(austinkrakatoa);

        User benlabas = new User();
        benlabas.firstName = "Ben";
        benlabas.lastName = "Labas";
        benlabas.email = "labody@yahoo.com";
        benlabas.password = "banspassword";
        benlabas.idType1 = "Passport";
        benlabas.addr1 = "45 Glen Oak View";
        benlabas.city = "Newington";
        benlabas.state = "Connecticut";
        benlabas.zip = 06111;
        benlabas.country = "USA";
        benlabas.id1 = 456789999;
        benlabas.date1Init = "3/02/04";
        benlabas.date1Exp = "3/12/22";
        benlabas.dateBirth = "9/13/2000";
        benlabas.gender = "M";
        benlabas.eyeColor = "Blue";
        benlabas.height = 190.5f;
        benlabas.weight = 210.3f;
        us.saveUser(benlabas);

        User mariabonventre = new User();
        mariabonventre.firstName = "Maria";
        mariabonventre.lastName = "Bonventre";
        mariabonventre.email = "Maria.Bonventre1@marist.edu";
        mariabonventre.password = "mariaspassword";
        mariabonventre.idType1 = "Passport";
        mariabonventre.addr1 = "25 Campus View Court";
        mariabonventre.city = "Poughkeepsie";
        mariabonventre.state = "New York";
        mariabonventre.zip = 12601;
        mariabonventre.country = "USA";
        mariabonventre.id1 = 24354256;
        mariabonventre.date1Init = "5/12/11";
        mariabonventre.date1Exp = "3/12/18";
        mariabonventre.dateBirth = "10/13/1997";
        mariabonventre.gender = "F";
        mariabonventre.eyeColor = "Brown";
        mariabonventre.height = 100.5f;
        mariabonventre.weight = 110.3f;
        us.saveUser(mariabonventre);

        User sabrinamcgee = new User();
        sabrinamcgee.firstName = "Sabrina";
        sabrinamcgee.lastName = "McGee";
        sabrinamcgee.email = "sabrina.mcgee1@marist.edu";
        sabrinamcgee.password = "sabspassword";
        sabrinamcgee.idType1 = "Driver License";
        sabrinamcgee.addr1 = "35 Campus View Court";
        sabrinamcgee.city = "Poughkeepsie";
        sabrinamcgee.state = "New York";
        sabrinamcgee.zip = 12601;
        sabrinamcgee.country = "USA";
        sabrinamcgee.id1 = 58568844;
        sabrinamcgee.date1Init = "3/12/11";
        sabrinamcgee.date1Exp = "3/12/16";
        sabrinamcgee.dateBirth = "4/13/1998";
        sabrinamcgee.gender = "F";
        sabrinamcgee.eyeColor = "Green";
        sabrinamcgee.height = 177.5f;
        sabrinamcgee.weight = 150.3f;
        us.saveUser(sabrinamcgee);

        User martakraw = new User();
        martakraw.firstName = "Marta";
        martakraw.lastName = "Kraw";
        martakraw.email = "krawcakes@gmail.com";
        martakraw.password = "martaspassword";
        martakraw.idType1 = "Passport";
        martakraw.addr1 = "4544 CandleWyck Lane";
        martakraw.city = "Newington";
        martakraw.state = "Connecticut";
        martakraw.zip = 06111;
        martakraw.country = "USA";
        martakraw.id1 = 349060;
        martakraw.date1Init = "3/12/13";
        martakraw.date1Exp = "3/12/19";
        martakraw.dateBirth = "4/13/1992";
        martakraw.gender = "F";
        martakraw.eyeColor = "Blue";
        martakraw.height = 177.5f;
        martakraw.weight = 150.3f;
        us.saveUser(martakraw);

        User daniellemason = new User();
        daniellemason.firstName = "Danielle";
        daniellemason.lastName = "Mason";
        daniellemason.email = "danielle.mason1@marist.edu";
        daniellemason.password = "daniellespassword";
        daniellemason.idType1 = "Driver License";
        daniellemason.addr1 = "333 bill road";
        daniellemason.city = "Buffalo";
        daniellemason.state = "New York";
        daniellemason.zip = 62676;
        daniellemason.country = "USA";
        daniellemason.id1 = 11256779;
        daniellemason.date1Init = "3/12/11";
        daniellemason.date1Exp = "3/12/16";
        daniellemason.dateBirth = "4/13/1998";
        daniellemason.gender = "F";
        daniellemason.eyeColor = "Blue";
        daniellemason.height = 177.5f;
        daniellemason.weight = 150.3f;
        us.saveUser(daniellemason);

        User billyhobbit = new User();
        billyhobbit.firstName = "Billy";
        billyhobbit.lastName = "Hobbit";
        billyhobbit.email = "billybhobbit@gmail.com";
        billyhobbit.password = "billyspassword";
        us.saveUser(billyhobbit);

        User boonepepe = new User();
        boonepepe.firstName = "Boone";
        boonepepe.lastName = "Pepe";
        boonepepe.email = "boonesboon@gmail.com";
        boonepepe.password = "boonespassword";
        us.saveUser(boonepepe);

        User ammonburke = new User();
        ammonburke.firstName = "Ammon";
        ammonburke.lastName = "Burke";
        ammonburke.email = "burkeinstocks@gmail.com";
        ammonburke.password = "ammonspassword";
        us.saveUser(ammonburke);

        User kraobrown = new User();
        kraobrown.firstName = "Krao";
        kraobrown.lastName = "Brown";
        kraobrown.email = "krao1@marist.edu";
        kraobrown.password = "kraospassword";
        us.saveUser(kraobrown);

        User antolinmarcus = new User();
        antolinmarcus.firstName = "Antolin";
        antolinmarcus.lastName = "Marcus";
        antolinmarcus.email = "antolinviolin@yahoo.com";
        antolinmarcus.password = "antolinspassword";
        us.saveUser(antolinmarcus);

        User kassidyjem = new User();
        kassidyjem.firstName = "Kassidy";
        kassidyjem.lastName = "Jem";
        kassidyjem.email = "jemsgems@bing.com";
        kassidyjem.password = "kaassidyspassword";
        us.saveUser(kassidyjem);

        User dannytuso = new User();
        dannytuso.firstName = "Danny";
        dannytuso.lastName = "Tuso";
        dannytuso.email = "dannytuso1@marist.edu";
        dannytuso.password = "dannyspassword";
        us.saveUser(dannytuso);

        User guntherwank = new User();
        guntherwank.firstName = "Gunther";
        guntherwank.lastName = "Wank";
        guntherwank.email = "guntheradventure@gmail.com";
        guntherwank.password = "guntherspassword";
        us.saveUser(guntherwank);

        User simonsalad = new User();
        simonsalad.firstName = "Simon";
        simonsalad.lastName = "Salad";
        simonsalad.email = "saladfingers@gmail.com";
        simonsalad.password = "saladspassword";
        us.saveUser(simonsalad);

        User sarakreek = new User();
        sarakreek.firstName = "Sara";
        sarakreek.lastName = "Kreek";
        sarakreek.email = "sara.kreek1@marist.edu";
        sarakreek.password = "saraspassword";
        us.saveUser(sarakreek);

        User ravenradiak = new User();
        ravenradiak.firstName = "Raven";
        ravenradiak.lastName = "Radiak";
        ravenradiak.email = "ravensorcrows@gmail.com";
        ravenradiak.password = "ravenspassword";
        us.saveUser(ravenradiak);

        User aydinhasson = new User();
        aydinhasson.firstName = "Aydin";
        aydinhasson.lastName = "Hasson";
        aydinhasson.email = "aydin.hasson1@marist.edu";
        aydinhasson.password = "aydenspassword";
        us.saveUser(aydinhasson);

        // give role assignments, half have more than 1 role
        UserRole timhoangrole1 = new UserRole();
        timhoangrole1.userId = timhoang.id;
        timhoangrole1.roleName = Role.Student;
        timhoangrole1.isActive = true;
        urs.saveUserRole(timhoangrole1);

        UserRole timhoangrole2 = new UserRole();
        timhoangrole2.userId = timhoang.id;
        timhoangrole2.roleName = Role.Security;
        timhoangrole2.isActive = true;
        urs.saveUserRole(timhoangrole2);

        UserRole derinyetilrole1 = new UserRole();
        derinyetilrole1.userId = derinyetil.id;
        derinyetilrole1.roleName = Role.Student;
        derinyetilrole1.isActive = true;
        urs.saveUserRole(derinyetilrole1);

        UserRole austinkrakatoarole1 = new UserRole();
        austinkrakatoarole1.userId = austinkrakatoa.id;
        austinkrakatoarole1.roleName = Role.Student;
        austinkrakatoarole1.isActive = true;
        urs.saveUserRole(austinkrakatoarole1);

        UserRole austinkrakatoarole2 = new UserRole();
        austinkrakatoarole2.userId = austinkrakatoa.id;
        austinkrakatoarole2.roleName = Role.Parent;
        austinkrakatoarole2.isActive = true;
        urs.saveUserRole(austinkrakatoarole2);

        UserRole benlabasrole1 = new UserRole();
        benlabasrole1.userId = benlabas.id;
        benlabasrole1.roleName = Role.Student;
        benlabasrole1.isActive = true;
        urs.saveUserRole(benlabasrole1);

        UserRole mariabonventrerole1 = new UserRole();
        mariabonventrerole1.userId = mariabonventre.id;
        mariabonventrerole1.roleName = Role.Student;
        mariabonventrerole1.isActive = true;
        urs.saveUserRole(mariabonventrerole1);

        UserRole mariabonventrerole2 = new UserRole();
        mariabonventrerole2.userId = mariabonventre.id;
        mariabonventrerole2.roleName = Role.Teacher;
        mariabonventrerole2.isActive = true;
        urs.saveUserRole(mariabonventrerole2);

        UserRole sabrinamcgeerole1 = new UserRole();
        sabrinamcgeerole1.userId = sabrinamcgee.id;
        sabrinamcgeerole1.roleName = Role.Student;
        sabrinamcgeerole1.isActive = true;
        urs.saveUserRole(sabrinamcgeerole1);

        UserRole martakrawrole1 = new UserRole();
        martakrawrole1.userId = martakraw.id;
        martakrawrole1.roleName = Role.Student;
        martakrawrole1.isActive = true;
        urs.saveUserRole(martakrawrole1);

        UserRole martakrawrole2 = new UserRole();
        martakrawrole2.userId = martakraw.id;
        martakrawrole2.roleName = Role.Security;
        martakrawrole2.isActive = true;
        urs.saveUserRole(martakrawrole2);

        UserRole daniellemasonrole1 = new UserRole();
        daniellemasonrole1.userId = daniellemason.id;
        daniellemasonrole1.roleName = Role.Student;
        daniellemasonrole1.isActive = true;
        urs.saveUserRole(daniellemasonrole1);

        UserRole billyhobbitrole1 = new UserRole();
        billyhobbitrole1.userId = billyhobbit.id;
        billyhobbitrole1.roleName = Role.Student;
        billyhobbitrole1.isActive = true;
        urs.saveUserRole(billyhobbitrole1);

        UserRole billyhobbitrole2 = new UserRole();
        billyhobbitrole2.userId = billyhobbit.id;
        billyhobbitrole2.roleName = Role.Security;
        billyhobbitrole2.isActive = true;
        urs.saveUserRole(billyhobbitrole2);

        UserRole boonepeperole1 = new UserRole();
        boonepeperole1.userId = boonepepe.id;
        boonepeperole1.roleName = Role.Student;
        boonepeperole1.isActive = true;
        urs.saveUserRole(boonepeperole1);

        UserRole ammonburkerole1 = new UserRole();
        ammonburkerole1.userId = ammonburke.id;
        ammonburkerole1.roleName = Role.Student;
        ammonburkerole1.isActive = true;
        urs.saveUserRole(ammonburkerole1);

        UserRole ammonburkerole2 = new UserRole();
        ammonburkerole2.userId = ammonburke.id;
        ammonburkerole2.roleName = Role.Parent;
        ammonburkerole2.isActive = true;
        urs.saveUserRole(ammonburkerole2);

        UserRole kraobrownrole1 = new UserRole();
        kraobrownrole1.userId = kraobrown.id;
        kraobrownrole1.roleName = Role.Student;
        kraobrownrole1.isActive = true;
        urs.saveUserRole(kraobrownrole1);

        UserRole antolinmarcusrole1 = new UserRole();
        antolinmarcusrole1.userId = antolinmarcus.id;
        antolinmarcusrole1.roleName = Role.Student;
        antolinmarcusrole1.isActive = true;
        urs.saveUserRole(antolinmarcusrole1);

        UserRole antolinmarcusrole2 = new UserRole();
        antolinmarcusrole2.userId = antolinmarcus.id;
        antolinmarcusrole2.roleName = Role.Parent;
        antolinmarcusrole2.isActive = true;
        urs.saveUserRole(antolinmarcusrole2);

        UserRole kassidyjemrole1 = new UserRole();
        kassidyjemrole1.userId = kassidyjem.id;
        kassidyjemrole1.roleName = Role.Student;
        kassidyjemrole1.isActive = true;
        urs.saveUserRole(kassidyjemrole1);

        UserRole dannytusorole1 = new UserRole();
        dannytusorole1.userId = dannytuso.id;
        dannytusorole1.roleName = Role.Student;
        dannytusorole1.isActive = true;
        urs.saveUserRole(dannytusorole1);

        UserRole dannytusorole2 = new UserRole();
        dannytusorole2.userId = dannytuso.id;
        dannytusorole2.roleName = Role.Parent;
        dannytusorole2.isActive = true;
        urs.saveUserRole(dannytusorole2);

        UserRole guntherwankrole1 = new UserRole();
        guntherwankrole1.userId = guntherwank.id;
        guntherwankrole1.roleName = Role.Student;
        guntherwankrole1.isActive = true;
        urs.saveUserRole(guntherwankrole1);

        UserRole simonsaladrole1 = new UserRole();
        simonsaladrole1.userId = simonsalad.id;
        simonsaladrole1.roleName = Role.Student;
        simonsaladrole1.isActive = true;
        urs.saveUserRole(simonsaladrole1);

        UserRole simonsaladrole2 = new UserRole();
        simonsaladrole2.userId = simonsalad.id;
        simonsaladrole2.roleName = Role.Security;
        simonsaladrole2.isActive = true;
        urs.saveUserRole(simonsaladrole2);

        UserRole sarakreekrole1 = new UserRole();
        sarakreekrole1.userId = sarakreek.id;
        sarakreekrole1.roleName = Role.Student;
        sarakreekrole1.isActive = true;
        urs.saveUserRole(sarakreekrole1);

        UserRole ravenradiakrole1 = new UserRole();
        ravenradiakrole1.userId = ravenradiak.id;
        ravenradiakrole1.roleName = Role.Student;
        ravenradiakrole1.isActive = true;
        urs.saveUserRole(ravenradiakrole1);

        UserRole ravenradiakrole2 = new UserRole();
        ravenradiakrole2.userId = ravenradiak.id;
        ravenradiakrole2.roleName = Role.Parent;
        ravenradiakrole2.isActive = true;
        urs.saveUserRole(ravenradiakrole2);

        UserRole aydinhassonrole1 = new UserRole();
        aydinhassonrole1.userId = aydinhasson.id;
        aydinhassonrole1.roleName = Role.Student;
        aydinhassonrole1.isActive = true;
        urs.saveUserRole(aydinhassonrole1);

        //Assign users to tenants. Tim and Gunther have both tenants.

        TenantUser murraytimhoang = new TenantUser();
        murraytimhoang.tenantId = murray.id;
        murraytimhoang.userId = timhoang.id;
        murraytimhoang.color = "blue";
        tus.saveTenantUser(murraytimhoang);

        TenantUser doyletimhoang = new TenantUser();
        doyletimhoang.tenantId = doyle.id;
        doyletimhoang.userId = timhoang.id;
        doyletimhoang.color = "black";
        tus.saveTenantUser(doyletimhoang);

        TenantUser murrayderinyetil = new TenantUser();
        murrayderinyetil.tenantId = murray.id;
        murrayderinyetil.userId = derinyetil.id;
        murrayderinyetil.color = "orange";
        tus.saveTenantUser(murrayderinyetil);
        
        TenantUser murrayaustinkrakatoa = new TenantUser();
        murrayaustinkrakatoa.tenantId = murray.id;
        murrayaustinkrakatoa.userId = austinkrakatoa.id;
        murrayaustinkrakatoa.color = "yellow";
        tus.saveTenantUser(murrayaustinkrakatoa);

        TenantUser murraybenlabas = new TenantUser();
        murraybenlabas.tenantId = murray.id;
        murraybenlabas.userId = benlabas.id;
        murraybenlabas.color = "green";
        tus.saveTenantUser(murraybenlabas);

        TenantUser murraybillyhobbit = new TenantUser();
        murraybillyhobbit.tenantId = murray.id;
        murraybillyhobbit.userId = billyhobbit.id;
        murraybillyhobbit.color = "blue";
        tus.saveTenantUser(murraybillyhobbit);

        TenantUser murrayboonepepe = new TenantUser();
        murrayboonepepe.tenantId = murray.id;
        murrayboonepepe.userId = boonepepe.id;
        murrayboonepepe.color = "red";
        tus.saveTenantUser(murrayboonepepe);

        TenantUser murrayammonburke = new TenantUser();
        murrayammonburke.tenantId = murray.id;
        murrayammonburke.userId = ammonburke.id;
        murrayammonburke.color = "brown";
        tus.saveTenantUser(murrayammonburke);

        TenantUser murraykraobrown = new TenantUser();
        murraykraobrown.tenantId = murray.id;
        murraykraobrown.userId = kraobrown.id;
        murraykraobrown.color = "violet";
        tus.saveTenantUser(murraykraobrown);

        TenantUser murraysimonsalad = new TenantUser();
        murraysimonsalad.tenantId = murray.id;
        murraysimonsalad.userId = simonsalad.id;
        murraysimonsalad.color = "green";
        tus.saveTenantUser(murraysimonsalad);

        TenantUser murraysarakreek = new TenantUser();
        murraysarakreek.tenantId = murray.id;
        murraysarakreek.userId = sarakreek.id;
        murraysarakreek.color = "yellow";
        tus.saveTenantUser(murraysarakreek);

        TenantUser doylemariabonventre = new TenantUser();
        doylemariabonventre.tenantId = doyle.id;
        doylemariabonventre.userId = mariabonventre.id;
        doylemariabonventre.color = "orange";
        tus.saveTenantUser(doylemariabonventre);

        TenantUser doylesabrinamcgee = new TenantUser();
        doylesabrinamcgee.tenantId = doyle.id;
        doylesabrinamcgee.userId = sabrinamcgee.id;
        doylesabrinamcgee.color = "blue";
        tus.saveTenantUser(doylesabrinamcgee);

        TenantUser doylemartakraw = new TenantUser();
        doylemartakraw.tenantId = doyle.id;
        doylemartakraw.userId = martakraw.id;
        doylemartakraw.color = "blue";
        tus.saveTenantUser(doylemartakraw);

        TenantUser doyledaniellemason = new TenantUser();
        doyledaniellemason.tenantId = doyle.id;
        doyledaniellemason.userId = daniellemason.id;
        doyledaniellemason.color = "violet";
        tus.saveTenantUser(doyledaniellemason);

        TenantUser doyleantolinmarcus = new TenantUser();
        doyleantolinmarcus.tenantId = doyle.id;
        doyleantolinmarcus.userId = antolinmarcus.id;
        doyleantolinmarcus.color = "green";
        tus.saveTenantUser(doyleantolinmarcus);

        TenantUser doylekassidyjem = new TenantUser();
        doylekassidyjem.tenantId = doyle.id;
        doylekassidyjem.userId = kassidyjem.id;
        doylekassidyjem.color = "pink";
        tus.saveTenantUser(doylekassidyjem);

        TenantUser doyledannytuso = new TenantUser();
        doyledannytuso.tenantId = doyle.id;
        doyledannytuso.userId = dannytuso.id;
        doyledannytuso.color = "brown";
        tus.saveTenantUser(doyledannytuso);

        TenantUser doyleguntherwank = new TenantUser();
        doyleguntherwank.tenantId = doyle.id;
        doyleguntherwank.userId = guntherwank.id;
        doyleguntherwank.color = "orange";
        tus.saveTenantUser(doyleguntherwank);

        TenantUser murrayguntherwank = new TenantUser();
        murrayguntherwank.tenantId = murray.id;
        murrayguntherwank.userId = guntherwank.id;
        murrayguntherwank.color = "orange";
        tus.saveTenantUser(murrayguntherwank);

        TenantUser doyleravenradiak = new TenantUser();
        doyleravenradiak.tenantId = doyle.id;
        doyleravenradiak.userId = ravenradiak.id;
        doyleravenradiak.color = "black";
        tus.saveTenantUser(doyleravenradiak);

        TenantUser doyleaydinhasson = new TenantUser();
        doyleaydinhasson.tenantId = doyle.id;
        doyleaydinhasson.userId = aydinhasson.id;
        doyleaydinhasson.color = "red";
        tus.saveTenantUser(doyleaydinhasson);

        //create beacons
        //used uuid as unique beacon, major as building, minor as floor

        //building 0 floor 0 tenant 1
        Beacon beacon1 = new Beacon();
        beacon1.UUID = 0;
        beacon1.majorId = 0;
        beacon1.minorId = 0;
        beacon1.tenantId = murray.id;
        bs.saveBeacon(beacon1);

        Beacon beacon2 = new Beacon();
        beacon2.UUID = 1;
        beacon2.majorId = 0;
        beacon2.minorId = 0;
        beacon2.tenantId = murray.id;
        bs.saveBeacon(beacon2);

        Beacon beacon3 = new Beacon();
        beacon3.UUID = 2;
        beacon3.majorId = 0;
        beacon3.minorId = 0;
        beacon3.tenantId = murray.id;
        bs.saveBeacon(beacon3);

        Beacon beacon4 = new Beacon();
        beacon4.UUID = 3;
        beacon4.majorId = 0;
        beacon4.minorId = 0;
        beacon4.tenantId = murray.id;
        bs.saveBeacon(beacon4);

        Beacon beacon5 = new Beacon();
        beacon5.UUID = 4;
        beacon5.majorId = 0;
        beacon5.minorId = 0;
        beacon5.tenantId = murray.id;
        bs.saveBeacon(beacon5);

        Beacon beacon6 = new Beacon();
        beacon6.UUID = 5;
        beacon6.majorId = 0;
        beacon6.minorId = 0;
        beacon6.tenantId = murray.id;
        bs.saveBeacon(beacon6);

        Beacon beacon7 = new Beacon();
        beacon7.UUID = 6;
        beacon7.majorId = 0;
        beacon7.minorId = 0;
        beacon7.tenantId = murray.id;
        bs.saveBeacon(beacon7);

        Beacon beacon8 = new Beacon();
        beacon8.UUID = 7;
        beacon8.majorId = 0;
        beacon8.minorId = 0;
        beacon8.tenantId = murray.id;
        bs.saveBeacon(beacon8);

        Beacon beacon9 = new Beacon();
        beacon9.UUID = 8;
        beacon9.majorId = 0;
        beacon9.minorId = 0;
        beacon9.tenantId = murray.id;
        bs.saveBeacon(beacon9);

        Beacon beacon10 = new Beacon();
        beacon10.UUID = 9;
        beacon10.majorId = 0;
        beacon10.minorId = 0;
        beacon10.tenantId = murray.id;
        bs.saveBeacon(beacon10);

        //building 1 floor 0 tenant 1
        Beacon beacon11 = new Beacon();
        beacon11.UUID = 10;
        beacon11.majorId = 1;
        beacon11.minorId = 0;
        beacon11.tenantId = murray.id;
        bs.saveBeacon(beacon11);

        Beacon beacon12 = new Beacon();
        beacon12.UUID = 11;
        beacon12.majorId = 1;
        beacon12.minorId = 0;
        beacon12.tenantId = murray.id;
        bs.saveBeacon(beacon12);

        Beacon beacon13 = new Beacon();
        beacon13.UUID = 12;
        beacon13.majorId = 1;
        beacon13.minorId = 0;
        beacon13.tenantId = murray.id;
        bs.saveBeacon(beacon13);

        Beacon beacon14 = new Beacon();
        beacon14.UUID = 13;
        beacon14.majorId = 1;
        beacon14.minorId = 0;
        beacon14.tenantId = murray.id;
        bs.saveBeacon(beacon14);

        Beacon beacon15 = new Beacon();
        beacon15.UUID = 14;
        beacon15.majorId = 1;
        beacon15.minorId = 0;
        beacon15.tenantId = murray.id;
        bs.saveBeacon(beacon15);

        Beacon beacon16 = new Beacon();
        beacon16.UUID = 15;
        beacon16.majorId = 1;
        beacon16.minorId = 0;
        beacon16.tenantId = murray.id;
        bs.saveBeacon(beacon16);

        Beacon beacon17 = new Beacon();
        beacon17.UUID = 16;
        beacon17.majorId = 1;
        beacon17.minorId = 0;
        beacon17.tenantId = murray.id;
        bs.saveBeacon(beacon17);

        Beacon beacon18 = new Beacon();
        beacon18.UUID = 17;
        beacon18.majorId = 1;
        beacon18.minorId = 0;
        beacon18.tenantId = murray.id;
        bs.saveBeacon(beacon18);

        Beacon beacon19 = new Beacon();
        beacon19.UUID = 18;
        beacon19.majorId = 1;
        beacon19.minorId = 0;
        beacon19.tenantId = murray.id;
        bs.saveBeacon(beacon19);

        Beacon beacon20 = new Beacon();
        beacon20.UUID = 19;
        beacon20.majorId = 1;
        beacon20.minorId = 0;
        beacon20.tenantId = murray.id;
        bs.saveBeacon(beacon20);

        //building 0 floor 0 tenant 2
        Beacon beacon21 = new Beacon();
        beacon21.UUID = 20;
        beacon21.majorId = 0;
        beacon21.minorId = 0;
        beacon21.tenantId = doyle.id;
        bs.saveBeacon(beacon21);

        Beacon beacon22 = new Beacon();
        beacon22.UUID = 21;
        beacon22.majorId = 0;
        beacon22.minorId = 0;
        beacon22.tenantId = doyle.id;
        bs.saveBeacon(beacon22);

        Beacon beacon23 = new Beacon();
        beacon23.UUID = 22;
        beacon23.majorId = 0;
        beacon23.minorId = 0;
        beacon23.tenantId = doyle.id;
        bs.saveBeacon(beacon23);

        Beacon beacon24 = new Beacon();
        beacon24.UUID = 23;
        beacon24.majorId = 0;
        beacon24.minorId = 0;
        beacon24.tenantId = doyle.id;
        bs.saveBeacon(beacon24);

        Beacon beacon25 = new Beacon();
        beacon25.UUID = 24;
        beacon25.majorId = 0;
        beacon25.minorId = 0;
        beacon25.tenantId = doyle.id;
        bs.saveBeacon(beacon25);

        Beacon beacon26 = new Beacon();
        beacon26.UUID = 25;
        beacon26.majorId = 0;
        beacon26.minorId = 0;
        beacon26.tenantId = doyle.id;
        bs.saveBeacon(beacon26);

        Beacon beacon27 = new Beacon();
        beacon27.UUID = 26;
        beacon27.majorId = 0;
        beacon27.minorId = 0;
        beacon27.tenantId = doyle.id;
        bs.saveBeacon(beacon27);

        Beacon beacon28 = new Beacon();
        beacon28.UUID = 27;
        beacon28.majorId = 0;
        beacon28.minorId = 0;
        beacon28.tenantId = doyle.id;
        bs.saveBeacon(beacon28);

        Beacon beacon29 = new Beacon();
        beacon29.UUID = 28;
        beacon29.majorId = 0;
        beacon29.minorId = 0;
        beacon29.tenantId = doyle.id;
        bs.saveBeacon(beacon29);

        Beacon beacon30 = new Beacon();
        beacon30.UUID = 29;
        beacon30.majorId = 0;
        beacon30.minorId = 0;
        beacon30.tenantId = doyle.id;
        bs.saveBeacon(beacon30);

        //building 0 floor 1 tenant 2
        Beacon beacon31 = new Beacon();
        beacon31.UUID = 30;
        beacon31.majorId = 0;
        beacon31.minorId = 1;
        beacon31.tenantId = doyle.id;
        bs.saveBeacon(beacon31);

        Beacon beacon32 = new Beacon();
        beacon32.UUID = 31;
        beacon32.majorId = 0;
        beacon32.minorId = 1;
        beacon32.tenantId = doyle.id;
        bs.saveBeacon(beacon32);

        Beacon beacon33 = new Beacon();
        beacon33.UUID = 32;
        beacon33.majorId = 0;
        beacon33.minorId = 1;
        beacon33.tenantId = doyle.id;
        bs.saveBeacon(beacon33);

        Beacon beacon34 = new Beacon();
        beacon34.UUID = 33;
        beacon34.majorId = 0;
        beacon34.minorId = 1;
        beacon34.tenantId = doyle.id;
        bs.saveBeacon(beacon34);

        Beacon beacon35 = new Beacon();
        beacon35.UUID = 34;
        beacon35.majorId = 0;
        beacon35.minorId = 1;
        beacon35.tenantId = doyle.id;
        bs.saveBeacon(beacon35);

        Beacon beacon36 = new Beacon();
        beacon36.UUID = 35;
        beacon36.majorId = 0;
        beacon36.minorId = 1;
        beacon36.tenantId = doyle.id;
        bs.saveBeacon(beacon36);

        Beacon beacon37 = new Beacon();
        beacon37.UUID = 36;
        beacon37.majorId = 0;
        beacon37.minorId = 1;
        beacon37.tenantId = doyle.id;
        bs.saveBeacon(beacon37);

        Beacon beacon38 = new Beacon();
        beacon38.UUID = 37;
        beacon38.majorId = 0;
        beacon38.minorId = 1;
        beacon38.tenantId = doyle.id;
        bs.saveBeacon(beacon38);

        Beacon beacon39 = new Beacon();
        beacon39.UUID = 38;
        beacon39.majorId = 0;
        beacon39.minorId = 1;
        beacon39.tenantId = doyle.id;
        bs.saveBeacon(beacon39);

        Beacon beacon40 = new Beacon();
        beacon40.UUID = 39;
        beacon40.majorId = 0;
        beacon40.minorId = 1;
        beacon40.tenantId = doyle.id;
        bs.saveBeacon(beacon40);

        //building 1 floor 0 tenant 2
        Beacon beacon41 = new Beacon();
        beacon41.UUID = 40;
        beacon41.majorId = 1;
        beacon41.minorId = 0;
        beacon41.tenantId = doyle.id;
        bs.saveBeacon(beacon41);

        Beacon beacon42 = new Beacon();
        beacon42.UUID = 41;
        beacon42.majorId = 1;
        beacon42.minorId = 0;
        beacon42.tenantId = doyle.id;
        bs.saveBeacon(beacon42);

        Beacon beacon43 = new Beacon();
        beacon43.UUID = 42;
        beacon43.majorId = 1;
        beacon43.minorId = 0;
        beacon43.tenantId = doyle.id;
        bs.saveBeacon(beacon43);

        Beacon beacon44 = new Beacon();
        beacon44.UUID = 43;
        beacon44.majorId = 1;
        beacon44.minorId = 0;
        beacon44.tenantId = doyle.id;
        bs.saveBeacon(beacon44);

        Beacon beacon45 = new Beacon();
        beacon45.UUID = 44;
        beacon45.majorId = 1;
        beacon45.minorId = 0;
        beacon45.tenantId = doyle.id;
        bs.saveBeacon(beacon45);

        Beacon beacon46 = new Beacon();
        beacon46.UUID = 45;
        beacon46.majorId = 1;
        beacon46.minorId = 0;
        beacon46.tenantId = doyle.id;
        bs.saveBeacon(beacon46);

        Beacon beacon47 = new Beacon();
        beacon47.UUID = 46;
        beacon47.majorId = 1;
        beacon47.minorId = 0;
        beacon47.tenantId = doyle.id;
        bs.saveBeacon(beacon47);

        Beacon beacon48 = new Beacon();
        beacon48.UUID = 47;
        beacon48.majorId = 1;
        beacon48.minorId = 0;
        beacon48.tenantId = doyle.id;
        bs.saveBeacon(beacon48);

        Beacon beacon49 = new Beacon();
        beacon49.UUID = 48;
        beacon49.majorId = 1;
        beacon49.minorId = 0;
        beacon49.tenantId = doyle.id;
        bs.saveBeacon(beacon49);

        Beacon beacon50 = new Beacon();
        beacon50.UUID = 49;
        beacon50.majorId = 1;
        beacon50.minorId = 0;
        beacon50.tenantId = doyle.id;
        bs.saveBeacon(beacon50);

        //create locations
        Location murrayhouse = new Location();
        murrayhouse.tenantId = murray.id;
        murrayhouse.name = "Murray House";
        murrayhouse.maxLat = 1000f;
        murrayhouse.maxLong = 1000f;
        murrayhouse.minLat = 0f;
        murrayhouse.minLong = 0f;
        ls.saveLocation(murrayhouse);

        Location murraygarage = new Location();
        murraygarage.tenantId = murray.id;
        murraygarage.name = "Murray Garage";
        murraygarage.maxLat = 3000f;
        murraygarage.maxLong = 3000f;
        murraygarage.minLat = 2000f;
        murraygarage.minLong = 2000f;
        ls.saveLocation(murraygarage);

        Location doylehouse = new Location();
        doylehouse.tenantId = doyle.id;
        doylehouse.name = "Doyle House";
        doylehouse.maxLat = 5000f;
        doylehouse.maxLong = 5000f;
        doylehouse.minLat = 4000f;
        doylehouse.minLong = 4000f;
        ls.saveLocation(doylehouse);

        Location doylegarage = new Location();
        doylegarage.tenantId = doyle.id;
        doylegarage.name = "Doyle Garage";
        doylegarage.maxLat = 7000f;
        doylegarage.maxLong = 7000f;
        doylegarage.minLat = 6000f;
        doylegarage.minLong = 6000f;
        ls.saveLocation(doylegarage);

        Floor murray1sthouse = new Floor();
        murray1sthouse.locationId = murrayhouse.id;
        murray1sthouse.tenantId = murray.id;
        murray1sthouse.name = "Murray House 1st Floor";
        murray1sthouse.maxLat = 1000f;
        murray1sthouse.maxLong = 1000f;
        murray1sthouse.maxAlt = 10f;
        murray1sthouse.minLat = 0f;
        murray1sthouse.minLong = 0f;
        murray1sthouse.minAlt = 0f;
        fs.saveFloor(murray1sthouse);

        Floor murray1stgarage = new Floor();
        murray1stgarage.locationId = murraygarage.id;
        murray1stgarage.tenantId = murray.id;
        murray1stgarage.name = "Murray Garage 1st Floor";
        murray1stgarage.maxLat = 3000f;
        murray1stgarage.maxLong = 3000f;
        murray1stgarage.maxAlt = 10f;
        murray1stgarage.minLat = 2000f;
        murray1stgarage.minLong = 2000f;
        murray1stgarage.minAlt = 0f;
        fs.saveFloor(murray1stgarage);

        Floor doyle1sthouse = new Floor();
        doyle1sthouse.locationId = doylehouse.id;
        doyle1sthouse.tenantId = doyle.id;
        doyle1sthouse.name = "Doyle House 1st Floor";
        doyle1sthouse.maxLat = 5000f;
        doyle1sthouse.maxLong = 5000f;
        doyle1sthouse.maxAlt = 10f;
        doyle1sthouse.minLat = 4000f;
        doyle1sthouse.minLong = 4000f;
        doyle1sthouse.minAlt = 0f;
        fs.saveFloor(doyle1sthouse);

        Floor doyle2ndhouse = new Floor();
        doyle2ndhouse.locationId = doylehouse.id;
        doyle2ndhouse.tenantId = doyle.id;
        doyle2ndhouse.name = "Doyle House 2nd Floor";
        doyle2ndhouse.maxLat = 5000f;
        doyle2ndhouse.maxLong = 5000f;
        doyle2ndhouse.maxAlt = 20f;
        doyle2ndhouse.minLat = 4000f;
        doyle2ndhouse.minLong = 4000f;
        doyle2ndhouse.minAlt = 11f;
        fs.saveFloor(doyle2ndhouse);

        Floor doyle1stgarage = new Floor();
        doyle1stgarage.locationId = doylegarage.id;
        doyle1stgarage.tenantId = doyle.id;
        doyle1stgarage.name = "Doyle Garage 1st Floor";
        doyle1stgarage.maxLat = 7000f;
        doyle1stgarage.maxLong = 7000f;
        doyle1stgarage.maxAlt = 10f;
        doyle1stgarage.minLat = 6000f;
        doyle1stgarage.minLong = 6000f;
        doyle1stgarage.minAlt = 0f;
        fs.saveFloor(doyle1stgarage);

        DeployedBeacon murray1sthouse1stbeacon = new DeployedBeacon();
        murray1sthouse1stbeacon.floorId = murray1sthouse.id;
        murray1sthouse1stbeacon.UUID = beacon1.UUID;
        murray1sthouse1stbeacon.majorId = beacon1.majorId;
        murray1sthouse1stbeacon.minorId = beacon1.minorId;
        murray1sthouse1stbeacon.lat = 0f;
        murray1sthouse1stbeacon.lon = 0f;
        murray1sthouse1stbeacon.alt = 0f;
        dbs.saveDeployedBeacon(murray1sthouse1stbeacon);

        DeployedBeacon murray1sthouse2ndbeacon = new DeployedBeacon();
        murray1sthouse2ndbeacon.floorId = murray1sthouse.id;
        murray1sthouse2ndbeacon.UUID = beacon2.UUID;
        murray1sthouse2ndbeacon.majorId = beacon2.majorId;
        murray1sthouse2ndbeacon.minorId = beacon2.minorId;
        murray1sthouse2ndbeacon.lat = 1000f;
        murray1sthouse2ndbeacon.lon = 1000f;
        murray1sthouse2ndbeacon.alt = 10f;
        dbs.saveDeployedBeacon(murray1sthouse2ndbeacon);

        DeployedBeacon murray1stgarage1stbeacon = new DeployedBeacon();
        murray1stgarage1stbeacon.floorId = murray1stgarage.id;
        murray1stgarage1stbeacon.UUID = beacon11.UUID;
        murray1stgarage1stbeacon.majorId = beacon11.majorId;
        murray1stgarage1stbeacon.minorId = beacon11.minorId;
        murray1stgarage1stbeacon.lat = 3000f;
        murray1stgarage1stbeacon.lon = 3000f;
        murray1stgarage1stbeacon.alt = 0f;
        dbs.saveDeployedBeacon(murray1stgarage1stbeacon);

        DeployedBeacon murray1stgarage2ndbeacon = new DeployedBeacon();
        murray1stgarage2ndbeacon.floorId = murray1stgarage.id;
        murray1stgarage2ndbeacon.UUID = beacon12.UUID;
        murray1stgarage2ndbeacon.majorId = beacon12.majorId;
        murray1stgarage2ndbeacon.minorId = beacon12.minorId;
        murray1stgarage2ndbeacon.lat = 2000f;
        murray1stgarage2ndbeacon.lon = 2000f;
        murray1stgarage2ndbeacon.alt = 10f;
        dbs.saveDeployedBeacon(murray1stgarage2ndbeacon);

        DeployedBeacon doyle1sthouse1stbeacon = new DeployedBeacon();
        doyle1sthouse1stbeacon.floorId = doyle1sthouse.id;
        doyle1sthouse1stbeacon.UUID = beacon21.UUID;
        doyle1sthouse1stbeacon.majorId = beacon21.majorId;
        doyle1sthouse1stbeacon.minorId = beacon21.minorId;
        doyle1sthouse1stbeacon.lat = 5000f;
        doyle1sthouse1stbeacon.lon = 5000f;
        doyle1sthouse1stbeacon.alt = 0f;
        dbs.saveDeployedBeacon(doyle1sthouse1stbeacon);

        DeployedBeacon doyle1sthouse2ndbeacon = new DeployedBeacon();
        doyle1sthouse2ndbeacon.floorId = doyle1sthouse.id;
        doyle1sthouse2ndbeacon.UUID = beacon22.UUID;
        doyle1sthouse2ndbeacon.majorId = beacon22.majorId;
        doyle1sthouse2ndbeacon.minorId = beacon22.minorId;
        doyle1sthouse2ndbeacon.lat = 4000f;
        doyle1sthouse2ndbeacon.lon = 4000f;
        doyle1sthouse2ndbeacon.alt = 10f;
        dbs.saveDeployedBeacon(doyle1sthouse2ndbeacon);

        DeployedBeacon doyle2ndhouse1stbeacon = new DeployedBeacon();
        doyle2ndhouse1stbeacon.floorId = doyle2ndhouse.id;
        doyle2ndhouse1stbeacon.UUID = beacon31.UUID;
        doyle2ndhouse1stbeacon.majorId = beacon31.majorId;
        doyle2ndhouse1stbeacon.minorId = beacon31.minorId;
        doyle2ndhouse1stbeacon.lat = 5000f;
        doyle2ndhouse1stbeacon.lon = 5000f;
        doyle2ndhouse1stbeacon.alt = 11f;
        dbs.saveDeployedBeacon(doyle2ndhouse1stbeacon);

        DeployedBeacon doyle2ndhouse2ndbeacon = new DeployedBeacon();
        doyle2ndhouse2ndbeacon.floorId = doyle2ndhouse.id;
        doyle2ndhouse2ndbeacon.UUID = beacon32.UUID;
        doyle2ndhouse2ndbeacon.majorId = beacon32.majorId;
        doyle2ndhouse2ndbeacon.minorId = beacon32.minorId;
        doyle2ndhouse2ndbeacon.lat = 4000f;
        doyle2ndhouse2ndbeacon.lon = 4000f;
        doyle2ndhouse2ndbeacon.alt = 20f;
        dbs.saveDeployedBeacon(doyle2ndhouse2ndbeacon);

        DeployedBeacon doyle1stgarage1stbeacon = new DeployedBeacon();
        doyle1stgarage1stbeacon.floorId = doyle1stgarage.id;
        doyle1stgarage1stbeacon.UUID = beacon41.UUID;
        doyle1stgarage1stbeacon.majorId = beacon41.majorId;
        doyle1stgarage1stbeacon.minorId = beacon41.minorId;
        doyle1stgarage1stbeacon.lat = 7000f;
        doyle1stgarage1stbeacon.lon = 7000f;
        doyle1stgarage1stbeacon.alt = 0f;
        dbs.saveDeployedBeacon(doyle1stgarage1stbeacon);

        DeployedBeacon doyle1stgarage2ndbeacon = new DeployedBeacon();
        doyle1stgarage2ndbeacon.floorId = doyle1stgarage.id;
        doyle1stgarage2ndbeacon.UUID = beacon2.UUID;
        doyle1stgarage2ndbeacon.majorId = beacon42.majorId;
        doyle1stgarage2ndbeacon.minorId = beacon42.minorId;
        doyle1stgarage2ndbeacon.lat = 6000f;
        doyle1stgarage2ndbeacon.lon = 6000f;
        doyle1stgarage2ndbeacon.alt = 10f;
        dbs.saveDeployedBeacon(doyle1stgarage2ndbeacon);

        //create destinations
        Destination destination1 = new Destination();
        destination1.tenantId = murray.id;
        destination1.state = State.JustArrived;
        destination1.lat = 20f;
        destination1.lon = 140f;
        destination1.alt = 5f;
        dests.saveDestination(destination1);

        //wait a bit after updating
        Thread.sleep(3000);

        Destination destination2 = new Destination();
        destination2.tenantId = murray.id;
        destination2.state = State.JustArrived;
        destination2.lat = 450f;
        destination2.lon = 720f;
        destination2.alt = 3f;
        dests.saveDestination(destination2);

        Destination destination3 = new Destination();
        destination3.tenantId = murray.id;
        destination3.state = State.CheckedIn;
        destination3.lat = 2600f;
        destination3.lon = 2850f;
        destination3.alt = 5f;
        dests.saveDestination(destination3);

        Destination destination4 = new Destination();
        destination4.tenantId = doyle.id;
        destination4.state = State.JustArrived;
        destination4.lat = 4200f;
        destination4.lon = 4500f;
        destination4.alt = 5f;
        dests.saveDestination(destination4);

        //wait a bit after updating
        Thread.sleep(3000);

        Destination destination5 = new Destination();
        destination5.tenantId = doyle.id;
        destination5.state = State.CheckedOut;
        destination5.lat = 4900f;
        destination5.lon = 4100f;
        destination5.alt = 8f;
        dests.saveDestination(destination5);

        Destination destination6 = new Destination();
        destination6.tenantId = doyle.id;
        destination6.state = State.JustArrived;
        destination6.lat = 4700f;
        destination6.lon = 4444f;
        destination6.alt = 14f;
        dests.saveDestination(destination6);

        //wait a bit after updating
        Thread.sleep(3000);

        Destination destination7 = new Destination();
        destination7.tenantId = doyle.id;
        destination7.state = State.CheckedIn;
        destination7.lat = 4339f;
        destination7.lon = 4686f;
        destination7.alt = 16f;
        dests.saveDestination(destination7);

        Destination destination8 = new Destination();
        destination8.tenantId = doyle.id;
        destination8.state = State.JustArrived;
        destination8.lat = 6666f;
        destination8.lon = 6464f;
        destination8.alt = 5f;
        dests.saveDestination(destination8);

        //create devices
        Device device1 = new Device();
        device1.userId = timhoang.id;
        device1.destinationId = destination1.id;
        devs.saveDevice(device1);

        Device device2 = new Device();
        device2.userId = derinyetil.id;
        device2.destinationId = destination2.id;
        devs.saveDevice(device2);

        Device device3 = new Device();
        device3.userId = austinkrakatoa.id;
        device3.destinationId = destination3.id;
        devs.saveDevice(device3);

        Device device4 = new Device();
        device4.userId = benlabas.id;
        device4.destinationId = destination4.id;
        devs.saveDevice(device4);

        Device device5 = new Device();
        device5.userId = mariabonventre.id;
        device5.destinationId = destination5.id;
        devs.saveDevice(device5);

        Device device6 = new Device();
        device6.userId = sabrinamcgee.id;
        device6.destinationId = destination6.id;
        devs.saveDevice(device6);

        Device device7 = new Device();
        device7.userId = martakraw.id;
        device7.destinationId = destination7.id;
        devs.saveDevice(device7);

        Device device8 = new Device();
        device8.userId = daniellemason.id;
        device8.destinationId = destination8.id;
        devs.saveDevice(device8);

        //create beacons read
        BeaconsRead beacon1device1 = new BeaconsRead();
        beacon1device1.deviceId = device1.id;
        beacon1device1.UUID = beacon1.UUID;
        beacon1device1.majorId = beacon1.majorId;
        beacon1device1.minorId = beacon1.minorId;
        beacon1device1.RSSI = 50;
        brs.saveBeaconsRead(beacon1device1);

        BeaconsRead beacon2device1 = new BeaconsRead();
        beacon2device1.deviceId = device1.id;
        beacon2device1.UUID = beacon2.UUID;
        beacon2device1.majorId = beacon2.majorId;
        beacon2device1.minorId = beacon2.minorId;
        beacon2device1.RSSI = 50;
        brs.saveBeaconsRead(beacon2device1);

        BeaconsRead beacon3device2 = new BeaconsRead();
        beacon3device2.deviceId = device2.id;
        beacon3device2.UUID = beacon3.UUID;
        beacon3device2.majorId = beacon3.majorId;
        beacon3device2.minorId = beacon3.minorId;
        beacon3device2.RSSI = 50;
        brs.saveBeaconsRead(beacon3device2);

        BeaconsRead beacon4device2 = new BeaconsRead();
        beacon4device2.deviceId = device2.id;
        beacon4device2.UUID = beacon4.UUID;
        beacon4device2.majorId = beacon4.majorId;
        beacon4device2.minorId = beacon4.minorId;
        beacon4device2.RSSI = 50;
        brs.saveBeaconsRead(beacon4device2);

        BeaconsRead beacon11device3 = new BeaconsRead();
        beacon11device3.deviceId = device3.id;
        beacon11device3.UUID = beacon11.UUID;
        beacon11device3.majorId = beacon11.majorId;
        beacon11device3.minorId = beacon11.minorId;
        beacon11device3.RSSI = 50;
        brs.saveBeaconsRead(beacon11device3);

        BeaconsRead beacon12device3 = new BeaconsRead();
        beacon12device3.deviceId = device3.id;
        beacon12device3.UUID = beacon12.UUID;
        beacon12device3.majorId = beacon12.majorId;
        beacon12device3.minorId = beacon12.minorId;
        beacon12device3.RSSI = 50;
        brs.saveBeaconsRead(beacon12device3);

        BeaconsRead beacon21device4 = new BeaconsRead();
        beacon21device4.deviceId = device4.id;
        beacon21device4.UUID = beacon21.UUID;
        beacon21device4.majorId = beacon21.majorId;
        beacon21device4.minorId = beacon21.minorId;
        beacon21device4.RSSI = 50;
        brs.saveBeaconsRead(beacon21device4);

        BeaconsRead beacon22device4 = new BeaconsRead();
        beacon22device4.deviceId = device4.id;
        beacon22device4.UUID = beacon22.UUID;
        beacon22device4.majorId = beacon22.majorId;
        beacon22device4.minorId = beacon22.minorId;
        beacon22device4.RSSI = 50;
        brs.saveBeaconsRead(beacon22device4);

        BeaconsRead beacon23device5 = new BeaconsRead();
        beacon23device5.deviceId = device5.id;
        beacon23device5.UUID = beacon23.UUID;
        beacon23device5.majorId = beacon23.majorId;
        beacon23device5.minorId = beacon23.minorId;
        beacon23device5.RSSI = 50;
        brs.saveBeaconsRead(beacon23device5);

        BeaconsRead beacon24device5 = new BeaconsRead();
        beacon24device5.deviceId = device5.id;
        beacon24device5.UUID = beacon24.UUID;
        beacon24device5.majorId = beacon24.majorId;
        beacon24device5.minorId = beacon24.minorId;
        beacon24device5.RSSI = 50;
        brs.saveBeaconsRead(beacon24device5);

        BeaconsRead beacon31device6 = new BeaconsRead();
        beacon31device6.deviceId = device6.id;
        beacon31device6.UUID = beacon31.UUID;
        beacon31device6.majorId = beacon31.majorId;
        beacon31device6.minorId = beacon31.minorId;
        beacon31device6.RSSI = 50;
        brs.saveBeaconsRead(beacon31device6);

        BeaconsRead beacon32device6 = new BeaconsRead();
        beacon32device6.deviceId = device6.id;
        beacon32device6.UUID = beacon32.UUID;
        beacon32device6.majorId = beacon32.majorId;
        beacon32device6.minorId = beacon32.minorId;
        beacon32device6.RSSI = 50;
        brs.saveBeaconsRead(beacon32device6);

        BeaconsRead beacon33device7 = new BeaconsRead();
        beacon33device7.deviceId = device7.id;
        beacon33device7.UUID = beacon33.UUID;
        beacon33device7.majorId = beacon33.majorId;
        beacon33device7.minorId = beacon33.minorId;
        beacon33device7.RSSI = 50;
        brs.saveBeaconsRead(beacon33device7);

        BeaconsRead beacon34device7 = new BeaconsRead();
        beacon34device7.deviceId = device7.id;
        beacon34device7.UUID = beacon34.UUID;
        beacon34device7.majorId = beacon34.majorId;
        beacon34device7.minorId = beacon34.minorId;
        beacon34device7.RSSI = 50;
        brs.saveBeaconsRead(beacon34device7);

        BeaconsRead beacon41device8 = new BeaconsRead();
        beacon41device8.deviceId = device8.id;
        beacon41device8.UUID = beacon41.UUID;
        beacon41device8.majorId = beacon41.majorId;
        beacon41device8.minorId = beacon41.minorId;
        beacon41device8.RSSI = 50;
        brs.saveBeaconsRead(beacon41device8);

        BeaconsRead beacon42device8 = new BeaconsRead();
        beacon42device8.deviceId = device8.id;
        beacon42device8.UUID = beacon42.UUID;
        beacon42device8.majorId = beacon42.majorId;
        beacon42device8.minorId = beacon42.minorId;
        beacon42device8.RSSI = 50;
        brs.saveBeaconsRead(beacon42device8);

        // get ready for updates
        Thread.sleep(3000);

        //query all tenants
        System.out.println("QUERYING TENANTS!!!");
        System.out.print("\n\n\n");
        
        for (int i=1; ts.getTenantById(i) != null; i++){
            System.out.println("Tenant " + ts.getTenantById(i).name + " Tenant Id = " + i);
        }
        System.out.print("\n\n\n");

        //query for all users for each tenant
        System.out.println("QUERYING USERS!!!");
        System.out.print("\n\n\n");
        
        for (int i=1; ts.getTenantById(i) != null; i++){ //go through all the tenants
            System.out.println("FOR TENANT " + ts.getTenantById(i).name);
            for (int j=1; tus.getTenantUserById(j) != null; j++){ //go though all the tenant users
                if (tus.getTenantUserById(j).tenantId == Long.valueOf(i)){ //if the tenant user tenantId = then tenant we are looking at
                    System.out.println(us.getUserById(tus.getTenantUserById(j).userId).firstName + " " + us.getUserById(tus.getTenantUserById(j).userId).lastName);
                }
            }
            System.out.print("\n\n\n");
        }

        //query for 2 users and thier roles output id number, firstname, lastname, and thier roles
        System.out.println("QUERYING USER ID of 5!!!");
        System.out.println("ID # is " + us.getUserById(5).id);
        System.out.println("First Name is " + us.getUserById(5).firstName);
        System.out.println("Last Name is " + us.getUserById(5).lastName);
        System.out.println("Roles are");
        for(int i=1; urs.getUserRoleById(i) != null; i++){ //Get roles with matching userId
            if (urs.getUserRoleById(i).userId == Long.valueOf(us.getUserById(5).id)){
                System.out.println(urs.getUserRoleById(i).roleName);
            }
        }

        System.out.print("\n\n\n");

        System.out.println("QUERYING USER ID of 13!!!");
        System.out.println("ID # is " + us.getUserById(13).id);
        System.out.println("First Name is " + us.getUserById(13).firstName);
        System.out.println("Last Name is " + us.getUserById(13).lastName);
        System.out.println("Roles are");
        for(int i=1; urs.getUserRoleById(i) != null; i++){
            if (urs.getUserRoleById(i).userId == Long.valueOf(us.getUserById(13).id)){
                System.out.println(urs.getUserRoleById(i).roleName);
            }
        }

        System.out.print("\n\n\n");

        //Modify 5 users
        System.out.println("MODIFYING 5 USERS AND DATA!!!");

        System.out.print("\n\n\n");

        mariabonventre.country = "Mexico";
        us.saveUser(mariabonventre);

        kassidyjem.email = "123qweasd@gmail.com";
        us.saveUser(kassidyjem);

        timhoang.firstName = "Timothy";
        us.saveUser(timhoang);

        sabrinamcgee.height = 250f;
        us.saveUser(sabrinamcgee);

        benlabas.weight = 1000f;
        us.saveUser(benlabas);


        //wait a bit after updating
        Thread.sleep(3000);

        //modify some again after the fact for fun
        mariabonventre.country = "Spain";
        us.saveUser(mariabonventre);

        timhoang.firstName = "TimTim";
        us.saveUser(timhoang);

        benlabas.weight = 103f;
        us.saveUser(benlabas);

        destination4.state = State.CheckedIn;
        dests.saveDestination(destination4);

        //wait a bit after updating a second time
        Thread.sleep(3000);

        timhoang.firstName = "T-dawg";
        us.saveUser(timhoang);

        benlabas.weight = 56f;
        us.saveUser(benlabas);

        destination4.state = State.CheckedOut;
        dests.saveDestination(destination4);


        //query for the five modified users
        System.out.println("User ID 5's current country is " + us.getUserWithHistoryById(5).current.country);

        System.out.println("------History-----");
        for(User userHistory : us.getUserWithHistoryById(5).history) {
            System.out.println("Start time: " + userHistory.arsd + " End time: " + userHistory.ared + " Historical Country: " + userHistory.country);
        }

        System.out.print("\n\n");

        System.out.println("User ID 14's current email is " + us.getUserWithHistoryById(14).current.email);

        System.out.println("------History-----");
        for(User userHistory : us.getUserWithHistoryById(14).history) {
            System.out.println("Start time: " + userHistory.arsd + " End time: " + userHistory.ared + " Historical Email: " + userHistory.email);
        }

        System.out.print("\n\n");

        System.out.println("User ID 1's current first name is " + us.getUserWithHistoryById(1).current.firstName);

        System.out.println("------History-----");
        for(User userHistory : us.getUserWithHistoryById(1).history) {
            System.out.println("Start time: " + userHistory.arsd + " End time: " + userHistory.ared + " Historical First Name: " + userHistory.firstName);
        }

        System.out.print("\n\n");

        System.out.println("User ID 6's current height is " + us.getUserWithHistoryById(6).current.height);

        System.out.println("------History-----");
        for(User userHistory : us.getUserWithHistoryById(6).history) {
            System.out.println("Start time: " + userHistory.arsd + " End time: " + userHistory.ared + " Historical Height: " + userHistory.height);
        }

        System.out.print("\n\n");

        System.out.println("User ID 4's current weight is " + us.getUserWithHistoryById(4).current.weight);

        System.out.println("------History-----");
        for(User userHistory : us.getUserWithHistoryById(4).history) {
            System.out.println("Start time: " + userHistory.arsd + " End time: " + userHistory.ared + " Historical Weight: " + userHistory.weight);
        }

        System.out.print("\n\n\n");

        //Query for all checkins by tenant (at least 2 result sets) display the full name of the user checked in, 
        //their destination, the time they checked in and the current status of their check in
        System.out.println("ALL CHECKINS BY TENANT!!!");
        System.out.print("\n\n");

        for (int i=1; ts.getTenantById(i) != null; i++){ //go through all the tenants
            System.out.println("FOR TENANT " + ts.getTenantById(i).name);

            for (int j=1; dests.getDestinationById(j) != null; j++){ //go through all the destinations
                if (ts.getTenantById(i).id == dests.getDestinationById(j).tenantId){//if the tenantId matches the destination tenantId
                    System.out.println("FOR DESTINATION " + dests.getDestinationById(j).id);

                    for (int k=1; devs.getDeviceById(k) != null; k++){ //go through all the devices
                        if(dests.getDestinationById(j).id == devs.getDeviceById(k).destinationId){ //if the destiantion id matches the device destination id
                            
                            for (int l=1; us.getUserById(l) != null; l++){//go through all the users
                                if(us.getUserById(l).id == devs.getDeviceById(k).userId){//if the userId matches the device userId
                                    System.out.println(us.getUserById(l).firstName + " " + us.getUserById(l).lastName + " has " + dests.getDestinationById(j).state + " at time " + dests.getDestinationById(j).arsd);
                                }

                            }
                        }

                    }

                }
    
            }
            System.out.print("\n\n");

        }

        //For 1 checkin that has gone through the complete cycle (all the way to completed / checked out) show the 
        //history of the event and show the times every change to the status happened.
        System.out.println("HISTORY OF ENTIRE CHECKIN CYCLE!!!");

        System.out.println("Destination 4's current state is " + dests.getDestinationWithHistoryById(4).current.state);

        System.out.println("------History-----");
        for(Destination destinationHistory : dests.getDestinationWithHistoryById(4).history) {
            System.out.println("Start time: " + destinationHistory.arsd + " End time: " + destinationHistory.ared + " Historical State: " + destinationHistory.state);
        }
        System.out.print("\n\n\n");

        //Query all the beacons located on floors in buildings for tenants. Output should first be grouped by tenant. 
        //For each tenant the locations (buildings) should be shown, then the floors for each building and finally the 
        //beacons located on each floor
        System.out.println("QUERYING ALL BEACONS LOCATED ON FLOORS IN BUILDINGS FOR TENANTS!!!");
        System.out.print("\n\n\n");

        for (int i=1; ts.getTenantById(i) != null; i++){ //go through all the tenants
            System.out.println("FOR TENANT " + ts.getTenantById(i).name);

            for (int j=1; ls.getLocationById(j) != null; j++){//go through all the locations
                if (ls.getLocationById(j).tenantId == ts.getTenantById(i).id){ //if the locations tenantId matches the tenantId
                    System.out.println("FOR LOCATION " + ls.getLocationById(j).name);

                    for (int k=1; fs.getFloorById(k) != null; k++){ //go through all the floors
                        if (fs.getFloorById(k).locationId == ls.getLocationById(j).id){ //if the floor locationId matches the locationId
                            System.out.println("FOR FLOOR " + fs.getFloorById(k).name);

                            for (int l=1; dbs.getDeployedBeaconById(l) != null; l++){ //go through all the deployed beacons
                                if (dbs.getDeployedBeaconById(l).floorId == fs.getFloorById(k).id){ //if the deployed beacon floorId matches the floorId
                                    System.out.println("BEACON " + bs.getBeaconById(l).UUID);
                                }
                            }

                        }    
                    }
                }

            }
            System.out.print("\n\n\n");

        }
        


    }
}