package com.example.lostandfound.Method;

import com.example.lostandfound.Entity.Item;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ManipulateItems {
    public List<Item> showAllItems() throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        List<Map> mapList=new DataBankAccess().query("item",false,"","","");
        List<Item> itemList = new ArrayList<>();
        getItemFromMapList(itemList,mapList);
        return itemList;
    }
    public List<Item> showSelectedItems(Item newItem) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        List<Map> mapList=new DataBankAccess().query("item",false,"type","=",newItem.getType());
        List<Item> itemList = new ArrayList<>();
        getItemFromMapList(itemList,mapList);
        mapList=new DataBankAccess().query("item",false,"name","=",newItem.getName());
        getItemFromMapList(itemList,mapList);
        return itemList;
    }
    public void addItem(Item newItem){
        try {
            new DataBankAccess().input("item",newItem.getType(),newItem.getName(),newItem.getContent(),newItem.getContact());
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    private void getItemFromMapList(List<Item> itemList, List<Map> mapList){
        for(Map map : mapList){
            Item item = new Item();
            item.setType(map.get("type").toString());
            item.setName(map.get("name").toString());
            item.setContent(map.get("content").toString());
            item.setContact(map.get("contact").toString());
            if(!itemList.contains(item)){
                itemList.add(item);
            };
        }
    }
}
