package finalproject.EcommerceApp;

import finalproject.EcommerceApp.model.Product;
import finalproject.EcommerceApp.model.ProductCategory;
import finalproject.EcommerceApp.model.ProductImages;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.repository.ProductRepository;
import finalproject.EcommerceApp.service.ProductCategoryService;
import finalproject.EcommerceApp.service.ProductImagesService;
import finalproject.EcommerceApp.service.ProductService;
import finalproject.EcommerceApp.service.SystemUserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static finalproject.EcommerceApp.model.CrystalCategory.*;

@Component
public class DataLoader {
    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImagesService productImagesService;

    @PostConstruct
    private void init() {
        SystemUser systemUser1 = SystemUser.builder()
                .email("Bob.head@example.com")
                .fullName("Bob Head")
                .userName("Bob.head")
                .externalUserId("YdGXYi8AYsZitW1gKRGOx1BYlgt2")
                .build();

        SystemUser systemUser2 = SystemUser.builder()
                .email("Bob.head1@example.com")
                .fullName("Bob Head")
                .userName("Bob.head")
                .externalUserId("4FNdPk8OwES6cBSi4Pf1Xv5IhEB2")
                .build();

        systemUserService.save(systemUser1);
        systemUserService.save(systemUser2);

        String woodString = WOOD.toString();
        String woodCategoryDescription = """
                Wood can break the ground (Earth).
                But Earth can bury Wood, too.
                Wood is the supporting element of Fire. Fire can release the power of Wood.
                """;
        ProductCategory wood = ProductCategory.builder()
                .categoryName(woodString)
                .categoryDescription(woodCategoryDescription)
                .build();

        productCategoryService.save(wood);

        String waterString = WATER.toString();
        String waterCategoryDescription = """
                Water can control and extinguish Fire. 
                But Fire might evaporate Water.
                Water is the supporting element of Wood. Wood can release the power of Water.
                """;
        ProductCategory water = ProductCategory.builder()
                .categoryName(waterString)
                .categoryDescription(waterCategoryDescription)
                .build();

        productCategoryService.save(water);

        String fireString = FIRE.toString();
        String fireCategoryDescription = """
                Fire can melt Metal. 
                But Metal might not melt before Fire is extinguished.
                Fire is the supporting element of Earth. Earth can release the power of Fire.""";
        ProductCategory fire = ProductCategory.builder()
                .categoryName(fireString)
                .categoryDescription(fireCategoryDescription)
                .build();

        productCategoryService.save(fire);

        String earthString = EARTH.toString();
        String earthCategoryDescription = """
                Earth can soak up Water, blocking its flow. 
                But Water can cover the land (Earth).
                Earth is the supporting element of Metal. Metal can release the power of Earth.""";
        ProductCategory earth = ProductCategory.builder()
                .categoryName(earthString)
                .categoryDescription(earthCategoryDescription)
                .build();
        productCategoryService.save(earth);

        String metalString = METAL.toString();
        String metalCategoryDescription = """
                Metal can cut Wood.
                But Metal might become dull before breaking Wood.
                Metal is the supporting element of Water. Water can release the power of Metal.""";
        ProductCategory metal = ProductCategory.builder()
                .categoryName(metalString)
                .categoryDescription(metalCategoryDescription)
                .build();
        productCategoryService.save(metal);

        int quantityOne = 1;
        String[] imagesList = {"Http1", "Http2"};

        Product productEarth = Product.builder()
                .title("黃晶塊\nCitrine Cluster")
                .description("""
                        色調從淺黃、明黃、金黃、酒黃到褐黃都有。黃色來自氧化鐵 Ferric Oxide.                       
                        Banded structures known as "tiger stripes", sometimes rutile is there. 
                        The yellow colour comes form Feric Oxide""")
                .price(new BigDecimal("100"))
                .color("顏色深淺不一 Light to deep yellow, brownish.")
                .countryOfOrigin("產地：巴西、西班牙、英國 From: Brazil, Spain, England")
                .quantity(quantityOne)
                .thumbnail("")
                .productCategory(earth)
                .build();
        productRepository.save(productEarth);
        productImagesService.save("Http1", productEarth);


        Product productFire = Product.builder()
                .title("紫晶塊\nAmethyst Cluster")
                .description("""
                        
                        有特別的內含物，看似虎紋、姆指紋或羽毛。又或含有針鐵礦內含物
                        Banded structures known as "tiger stripes", sometimes rutile is there.""")
                .price(new BigDecimal("150"))
                .color("""
                        顏色淺紫至深紫
                        light purple to deep purple
                        """)
                .countryOfOrigin("""
                        產地：巴西、前蘇聯烏拉爾、加拿大、斯里蘭卡、印度、烏拉圭、馬達加斯加、美國、德國、澳洲、納米比亞
                        From Brazil, Russia, Canada, Sri Lanka, India, Uruguay, Madagascar, USA, Germany, Australia, Namibia
                        """)
                .quantity(quantityOne)
                .thumbnail("")
                .productCategory(fire)
                .build();
        productRepository.save(productFire);
        productImagesService.save("Http1", productFire);



        Product productWood = Product.builder()
                .title("綠紋石/阿富汗玉/碳酸鹽玉\nAfghanistan Green Calcite/ Afghanistan Jade")
                .description("""
                        綠紋石的主要成分是碳酸鈣，綠色的紋理是鐵鋅鎳共同作用的結果；
                        致色成因是鐵元素，內含層狀分布的綠泥石。
                        大理石是一種變質岩，由碳酸鹽岩經區域變質作用或接觸變質作用形成
                        Main composition is Calcium Carbonate, the Green stripes is the interaction between iron, zinc and nickel.
                        Colour element is iron, the layering inclusion is chlorite.""")
                .price(new BigDecimal("199"))
                .color("""
                        顏色淺紫至深紫
                        多為淡綠色，間有乳白色條紋 Mostly light green, with milky white stripes
                        """)
                .countryOfOrigin("""
                        產地：阿富汗 From Afghanistan
                        """)
                .quantity(quantityOne)
                .thumbnail("")
                .productCategory(wood)
                .build();

        productRepository.save(productWood);
        productImagesService.save("Http1", productWood);


    }
}
