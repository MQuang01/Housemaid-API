package com.cghue.projecthousemaidwebapp.utils;

public class SendEmail {
    public static String EmailCooperate(String username, String sign) {
        return "Kính gửi " + username + ",\n\n"
                + "Cảm ơn bạn đã đăng ký hợp tác thành công với chúng tôi!\n"
                + "Chúng tôi sẽ liên hệ với bạn trong thời gian sớm nhất để hoàn thiện thủ tục hợp tác.\n"
                + "Xin chân thành cảm ơn sự hợp tác của bạn.\n\n"
                + "Trân trọng,\n\n"
                + sign;
    }
    public static String EmailResetPassword(String username, String code, String sign) {
        return "Kính gửi " + username + ",\n\n"
                + "Bạn đã yêu cầu đặt lại mật khẩu cho tài khoản của mình.\n"
                + "Vui lòng nhập mã " + code + " để đặt lại mật khẩu:\n"
                + "Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.\n\n"
                + "Trân trọng,\n\n"
                + sign;
    }


    public static String EmailScheduledSuccessfully(String username, String day,String time, String url, String sign) {
        return "Kính gửi " + username + ",\n\n"
                + "Cảm ơn quý khách đã sử dụng dịch vụ của chúng tôi!\n"
                + "Bạn đã đặt lịch vệ sinh thành công tại HouseMaid System.\n"
                + "Thông tin đặt lịch:\n"
                + "Ngày làm việc: " + day + "\n"
                + "Thời gian: " + time + "\n\n"
                + "Vui lòng xác nhận đặt lịch vệ sinh bằng cách truy cập đường liên kết sau:\n"
                + url + "\n\n"
                + "Cám ơn bạn đã sử dụng dịch vụ của chúng tôi.\n\n"
                + "Chúc bạn một ngày tốt lành!\n\n"
                + "Trân trọng,\n\n"
                + sign;
    }
    public static String ExamScheduleReminder(String name, String bookingDate, String bookingTime, String sign) {
        return "Xin chào " + name + ",\n\n"
                + "Bạn đã đặt lịch vệ sinh vào ngày " + bookingDate + " vào lúc " + bookingTime + "\n"
                + "Chúng tôi sẽ đến đúng giờ!\n"
                + "Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi.\n\n"
                + "Trân trọng,\n\n"
                + sign;
    }
}

