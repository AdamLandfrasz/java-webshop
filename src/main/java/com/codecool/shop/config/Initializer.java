package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier sony = new Supplier("Sony Interactive Entertainment", "Video games and gaming console network services");
        Supplier rockstar = new Supplier("Rockstar Games", "Video game development");
        Supplier ea = new Supplier("Electronic Arts", "Video game development");
        Supplier ubisoft = new Supplier("Ubisoft Entertainment", "Video game development");
        Supplier activision = new Supplier("Activision Publishing", "Video game development");
        Supplier cdpr = new Supplier("CD Project", "Video game development");
        Supplier nintendo = new Supplier("Nintendo Company", "Video game development");

        supplierDataStore.add(sony);
        supplierDataStore.add(rockstar);
        supplierDataStore.add(ea);
        supplierDataStore.add(ubisoft);
        supplierDataStore.add(activision);
        supplierDataStore.add(cdpr);
        supplierDataStore.add(nintendo);

        //setting up a new product category
        ProductCategory ps4 = new ProductCategory("PlayStation 4", "Gaming Platform", "Gaming console developed and maintained by Sony Entertainment Inc.");
        ProductCategory xBoxOne = new ProductCategory("XboxOne", "Gaming Platform", "Gaming console developed and maintained by Microsoft.");
        ProductCategory pc = new ProductCategory("PC", "Gaming Platform", "Personal computer");
        ProductCategory nintendoSwitch = new ProductCategory("Nintendo Switch", "Gaming Platform", "Gaming console developed and maintained by Nintendo.");

        productCategoryDataStore.add(ps4);
        productCategoryDataStore.add(xBoxOne);
        productCategoryDataStore.add(pc);
        productCategoryDataStore.add(nintendoSwitch);

        //setting up products and printing it
        productDataStore.add(new Product("God of War", 59.99f, "EUR", "Journey into the fearsome Norse wilds where Kratos must master his legendary rage - not only to learn from his bloody past... but to give his son a future.", ps4, sony));
        productDataStore.add(new Product("Marvel's Spider-Man", 59.99f, "EUR", "Swing into an action-packed open-world full of deadly super villains and high personal stakes - put on the iconic mask as Spider-man in a brand new and authentic adventure.", ps4, sony));
        productDataStore.add(new Product("Death Stranding", 59.99f, "EUR", "Sam Bridges must brave a world utterly transformed by the Death Stranding. Carrying the disconnected remnants of our future in his hands, he embarks on a journey to reconnect the shattered world one step at a time.", ps4, sony));
        productDataStore.add(new Product("Death Stranding", 59.99f, "EUR", "Sam Bridges must brave a world utterly transformed by the Death Stranding. Carrying the disconnected remnants of our future in his hands, he embarks on a journey to reconnect the shattered world one step at a time.", pc, sony));
        productDataStore.add(new Product("Red Dead Redemption 2", 59.99f, "EUR", "Th end of the wild west era has began as lawmen hunt down the last remaining outlaw gangs.", ps4, rockstar));
        productDataStore.add(new Product("Red Dead Redemption 2", 59.99f, "EUR", "Th end of the wild west era has began as lawmen hunt down the last remaining outlaw gangs.", xBoxOne, rockstar));
        productDataStore.add(new Product("Red Dead Redemption 2", 59.99f, "EUR", "Th end of the wild west era has began as lawmen hunt down the last remaining outlaw gangs.", pc, rockstar));
        productDataStore.add(new Product("Call of Duty: Modern Warfare", 59.99f, "EUR", "Take on the role of lethal Tier One operators in a heart-racing saga that will affect the global balance of power.", ps4, activision));
        productDataStore.add(new Product("Call of Duty: Modern Warfare", 59.99f, "EUR", "Take on the role of lethal Tier One operators in a heart-racing saga that will affect the global balance of power.", xBoxOne, activision));
        productDataStore.add(new Product("Call of Duty: Modern Warfare", 59.99f, "EUR", "Take on the role of lethal Tier One operators in a heart-racing saga that will affect the global balance of power.", pc, activision));
        productDataStore.add(new Product("The Witcher 3: Wild Hunt", 59.99f, "EUR", "Become monster slayer Geralt of Rivia and take on the most important contract of your life - tracking down the Child of Prophecy, a living weapon that can alter the shape of the world.", ps4, cdpr));
        productDataStore.add(new Product("The Witcher 3: Wild Hunt", 59.99f, "EUR", "Become monster slayer Geralt of Rivia and take on the most important contract of your life - tracking down the Child of Prophecy, a living weapon that can alter the shape of the world.", xBoxOne, cdpr));
        productDataStore.add(new Product("The Witcher 3: Wild Hunt", 59.99f, "EUR", "Become monster slayer Geralt of Rivia and take on the most important contract of your life - tracking down the Child of Prophecy, a living weapon that can alter the shape of the world.", pc, cdpr));
        productDataStore.add(new Product("The Witcher 3: Wild Hunt", 59.99f, "EUR", "Become monster slayer Geralt of Rivia and take on the most important contract of your life - tracking down the Child of Prophecy, a living weapon that can alter the shape of the world.", nintendoSwitch, cdpr));
        productDataStore.add(new Product("Super Smash Bros. Ultimate", 59.99f, "EUR", "A series of crossover fighting video games published by Nintendo, and primarily features characters of various Nintendo franchises", nintendoSwitch, nintendo));
    }
}
