<?php 

	require "connect1.php";

	$taikhoan = $_POST['taikhoan']; // Lấy ra param "taikhoan" từ trong API client gửi lên
	$matkhau = $_POST['matkhau']; // Lấy ra param "matkhau" từ trong API client gửi lên
	$hinhanh = $_POST['hinhanh']; // Lấy ra param "hinhanh" từ trong API client gửi lên

	// Nếu các param client gửi lên khác rỗng thì xử lý tiếp
	if(strlen($taikhoan) > 0 && strlen($matkhau) > 0 && strlen($hinhanh) > 0){

		// Tạo 1 câu truy vấn = SQL để set data vào DataBase của server
		// Đã có table "sinhvien" trong database
		// (null,'$taikhoan','$matkhau','$hinhanh') là các cột trong database
		$query123 = "INSERT INTO sinhvien VALUES (null,'$taikhoan','$matkhau','$hinhanh')";

		// thực thi truy vấn sql
		$data123 = mysqli_query($connect123,$query123);
		if($data123){
			echo "Success";
		} else {
			echo "FAIL";
		}

	} else {
		echo "NULL";
	}

 ?>