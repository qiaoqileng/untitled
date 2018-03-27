#sql("join_us")
    UPDATE user SET qq=?,email=?,shop_url=?,shop_address=?,shop_brand=?,shop_taobao_url=?,shop_create_time=?,shop_type=?,shop_style=? WHERE name=?
#end
#sql("find_by_open_id")
    SELECT * FROM user WHERE user.open_id=?
#end