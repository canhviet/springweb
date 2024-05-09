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

function clearSearchThongKeTTSD() {
    window.location = "/admin/thongke/TTSD"
}

function clearSearchThongKeTBDangMuon() {
    window.location = "/admin/thongke/TBDangMuon"
}
