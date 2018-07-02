<?php 

	$file_path = "image/"; // noi chua file upload tư client len server

	// Đơn giản nhất, uploaded_file là tham số cần truyền vào API và yêu cầu client phải gửi lên.
	// Từ đó, server sẽ dựa vào cái "uploaded_file" này để lấy data từ client gửi lên.

	// Sau khi upload len, file sẽ nằm trong thư mục "tmp_name" để lấy ra được dữ liệu ra thì dùng biến $_FILES
	// $_FILES['uploaded_file']['name'] => uploaded_file  là biến chứa file đã update, do mình đặt
	// name là thuôc tính muốn lấy, ở đây là lấy tên(name).
	//Có một biến PHP toàn cục là $_FILES. Biến này là một mảng liên hợp và giữ tất cả thông tin liên quan đến file được tải lên. Vì vậy, nếu giá trị gán cho thuộc tính name của input trong form upload là file123, khi đó PHP có thể tạo 5 biến sau:

	//$_FILES['file123']['tmp_name'] − File đã upload trong thư mục tạm thời trên Web Server.
	//$_FILES['file123']['name'] − Tên thực sự của file đã upload.
	//$_FILES['file123']['size'] − Kích thước tính theo byte của file đã upload.
	//$_FILES['file123']['type'] − Kiểu MIME của file đã upload.
	//$_FILES['file123']['error'] − Mã hóa lỗi liên quan đến file tải lên này.
	$file_path = $file_path.basename($_FILES['uploaded_file']['name']); 


	// Sau đó sẽ đẩy cái file từ thư mục tmp_name vào trong folder lưu trữ đã tạo ở trên
	if (move_uploaded_file($_FILES['uploaded_file']['tmp_name'],$file_path)) {
		echo $_FILES['uploaded_file']['name'];
	} else {
		echo "Failed";
	}
	

 ?>