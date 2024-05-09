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
    window.location = "/admin/thanhvien";
}

function clearSearchThietBi() {
    window.location = "/admin/thietbi";
}

function clearSearchThongKeTTSD() {
    window.location = "/admin/thongke/TTSD"
}

function clearSearchThongKeTBDangMuon() {
    window.location = "/admin/thongke/TBDangMuon"
}

function clearSearchXuly() {
    window.location = "/admin/xuly";
}