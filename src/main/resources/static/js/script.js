//jquery xoa thanh vien
$(document).ready(function () {
    $("a.delete_thanhvien").on("click", function (event) {
        var id = $(this).attr('id');
        if (confirm("Bạn Muốn Xóa Thành Viên " + id + "?")) {
        } else {
            event.preventDefault();
        }
    });
});

function clearSearch() {
    window.location = "/thanhvien";
}

function clearSearchThietBi() {
    window.location = "/user";
}

//add thanh vien
// $(document).ready(function() {
//     $('#register').submit(function(e) {
//         e.preventDefault();
//
//         var data = {
//             name: $('#name').val(),
//             email: $('#email').val()
//         };
//
//         $.ajax({
//             url: '',
//             method: 'POST',
//             contentType: 'application/json',
//             data: JSON.stringify(data),
//             success: function(response) {
//                 if (response.success) {
//                     alert('Thêm thành công');
//                 } else {
//                     alert('Thêm thất bại');
//                 }
//             }
//         });
//     });
// });