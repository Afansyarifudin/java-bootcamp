package com.example.challenge3.service;

import com.example.challenge3.model.ListFood;
import lombok.Getter;

import java.util.List;

@Getter
public class FoodService {
    private List<ListFood> listFood;


    // Constructor
    public FoodService() {
        listFood = List.of(
                new ListFood(1, "Nasi Goreng", 15000),
                new ListFood(2, "Mie Goreng", 13000),
                new ListFood(3, "Nasi + Ayam", 18000),
                new ListFood(4, "Es Teh Manis", 3000),
                new ListFood(5, "Es Jeruk", 5000)
        );
    }

//    public static void main(String[] args) {
//        List<ListFood> list = Arrays.asList(
//                new ListFood(1, "Nasi Goreng", 15000),
//                new ListFood(2, "Mie Goreng", 13000),
//                new ListFood(3, "Nasi + Ayam", 18000),
//                new ListFood(4, "Es Teh Manis", 3000),
//                new ListFood(5, "Es Jeruk", 5000)
//        );
//
////        ComparatorProviderStatic comparatorProvider = new ComparatorProviderStatic();
//
////        list.sort(new Comparator<ListFood>() {
////            @Override
////            public int compare(ListFood o1, ListFood o2) {
////                return comparatorProvider.compareByPrice(o1, o2);
////            }
////        });
//
//        list.sort(ComparatorProviderStatic::compareByPrice);
//        list.forEach(food -> System.out.println(food));
//    }
//
//    class ComparatorProviderStatic {
//        public static int compareByPrice(ListFood food1, ListFood food2) {
//            return Long.compare(food1.getPrice(), food2.getPrice());
//        }
//    }
}


