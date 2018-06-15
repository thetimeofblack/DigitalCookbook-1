# Digital Cookbook

This is the cookbook system created by Group3 of Software Engineering II in University of Applied Sciences in Luebeck, Germany, 2018.

@author Hua Yichen, Shan Jiaxiang, Kong Yu, Wang Jungang.

删除或添加step时,修改step以下所有的stepOrder,最后保存时全部update;删除时直接跳出Alert,若用户点击确认,直接删除。

整体添加时,先添加recipe information,返回一个recipeID,将所有的ingredient和steps设置ownerRecipeID后加入