package com.paperscissorsstonegame;

/**
 * Created by Thian He on 11/4/2018.
 */

public class NewMethod {

    public String getComputerPlay(int computerPlay){
        // 1 – 剪刀, 2 – 石頭, 3 – 布.
        if(computerPlay == 1){
            return "剪刀";
        }
        else if(computerPlay == 2){
            return "石頭";
        }
        else{
            return "布";
        }
    }
    public String getResult(int playerPlay,int computerPlay){
        if(playerPlay == computerPlay){
            return "判定輸贏：雙方平手！";
        }else if((playerPlay < computerPlay && !(playerPlay==1 && computerPlay==3)) || (playerPlay == 3 && computerPlay ==1)){
            return "判定輸贏：很可惜，你輸了！";
        }else{
            return "判定輸贏：恭喜，你贏了！";
        }
    }

}


