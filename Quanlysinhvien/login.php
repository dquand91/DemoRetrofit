<?php 

	require "connect1.php";

	$taikhoan = $_POST['taikhoan123'];
	$matkhau = $_POST['matkhau123'];

	// $taikhoan = "aaa";
	// $matkhau = "123";

	class SinhVien{
		function SinhVien($id, $user,$password,$hinhanh){
			$this->id = $id;
			$this->Taikhoan = $user;
			$this->Matkhau = $password;
			$this->Hinhanh = $hinhanh;
		}
	}

	if(strlen($taikhoan) > 0 && strlen($matkhau) > 0){
		$mangSinhVien = array();

		$query = "SELECT * FROM sinhvien WHERE FIND_IN_SET('$taikhoan',taikhoan) AND FIND_IN_SET('$matkhau', matkhau)";

		$data = mysqli_query($connect123, $query);
		if($data){
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($mangSinhVien, new SinhVien($row['id']
														,$row['taikhoan']
														,$row['matkhau']
														,$row['hinhanh']));
			}
			if(count($mangSinhVien) > 0){
				echo json_encode($mangSinhVien);
			} else {
				echo "Fail. Tai khoan Khong dung";
			}
		}
	} else {
		echo "Null";
	}

 ?>