SELECT * FROM FOOD_STORE

SELECT * FROM FS_MENU

SELECT fs.fs_code, fs.fs_name, fs.fs_latitude, fs.fs_longitude
      ,fs.fs_size, fs.fs_opentime, fs.fs_closetime
      ,fs.fs_good, fs.fs_bad, fs.fs_picture, fs.fs_juso
      ,fsm.fsm_name, fsm.fsm_price
  FROM FOOD_STORE fs, FS_menu fsm
 WHERE fs.fsm_code = fsm.fsm_code
 
   AND fs.fs_name = '�ﱸġŲ';