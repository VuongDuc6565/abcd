import numpy as np

# --- Tham số ---
cities = ["Hanoi", "HCM", "DaNang", "HaiPhong", "CanTho", "QuangNinh"]
T = 30  # Số ngày
N = {"Hanoi": 8500000, "HCM": 9200000, "DaNang": 1150000, 
     "HaiPhong": 2000000, "CanTho": 1300000, "QuangNinh": 1320000}
I_0 = {"Hanoi": 1500, "HCM": 2000, "DaNang": 300, 
       "HaiPhong": 400, "CanTho": 350, "QuangNinh": 200}
E_0 = {"Hanoi": 500, "HCM": 700, "DaNang": 100, 
       "HaiPhong": 150, "CanTho": 120, "QuangNinh": 80}
S_0 = {c: N[c] - I_0[c] - E_0[c] for c in cities}
R_0 = {c: 0 for c in cities}
H = {"Hanoi": 100, "HCM": 120, "DaNang": 20, 
     "HaiPhong": 30, "CanTho": 25, "QuangNinh": 15}
R_h = 300  # Năng suất tiêm trung bình mỗi cơ sở mỗi ngày
V_total = 1000000  # Tổng số vaccine có thể phân phối
beta = 0.3 # Tỷ lệ lây nhiễm (đồng nhất cho các thành phố)
alpha = 1/5.2  # Tỷ lệ chuyển từ E sang I
gamma = 0.1  # Tỷ lệ hồi phục
epsilon = 50  # Ngưỡng ưu tiên dựa trên tốc độ tăng \( E \)
v_min = 100  # Lượng vaccine tối thiểu ưu tiên

# --- Tính năng lực tiêm chủng ---
C = {c: H[c] * R_h for c in cities}

# --- Mảng lưu trữ kết quả ---
dp = {}  # Mảng lưu trữ số ca nhiễm tối thiểu và vaccine phân phối

# --- Phương trình SEIR ---
def SEIR_step(S_t, E_t, I_t, R_t, vaccine_t, city):
    """
    Tính toán trạng thái SEIR tại thời điểm t+1 sau khi phân phối vaccine_t.
    """
    new_S = S_t - beta * S_t * I_t / N[city] - vaccine_t  # Số người dễ nhiễm sau khi tiêm
    new_E = E_t + beta * S_t * I_t / N[city] - alpha * E_t  # Số người ủ bệnh
    new_I = I_t + alpha * E_t - gamma * I_t  # Số người nhiễm
    new_R = R_t + gamma * I_t + vaccine_t  # Số người hồi phục

    return new_S, new_E, new_I, new_R

# --- Quy hoạch động ---
# Tạo mảng dp để lưu trữ các kết quả
# dp[day][city] = (S_t, E_t, I_t, R_t, vaccine_t)
for t in range(T+1):
    dp[t] = {}

# Khởi tạo giá trị ban đầu cho tất cả các thành phố
for city in cities:
    dp[0][city] = (S_0[city], E_0[city], I_0[city], R_0[city], 0)

# Lặp qua các ngày và tính toán số vaccine cần phân phối
for t in range(1, T+1):
    for city in cities:
        # Lấy trạng thái của ngày trước đó
        S_t, E_t, I_t, R_t, prev_vaccine = dp[t-1][city]

        # Tối ưu hóa số vaccine phân phối trong ngày t
        best_vaccine = 0
        min_infected = float('inf')

        # Kiểm tra các mức phân phối vaccine từ 0 đến tối đa năng lực tiêm chủng
        for vaccine_t in range(0, min(C[city], V_total) + 1):
            new_S, new_E, new_I, new_R = SEIR_step(S_t, E_t, I_t, R_t, vaccine_t, city)
            infected = new_I  # Số ca nhiễm trong ngày cuối

            if infected < min_infected:
                best_vaccine = vaccine_t
                min_infected = infected
        
        # Lưu kết quả tối ưu cho ngày t
        dp[t][city] = SEIR_step(S_t, E_t, I_t, R_t, best_vaccine, city) + (best_vaccine,)

# --- In kết quả ---
print("Phân phối vaccine tối ưu:")
for t in range(T):
    for city in cities:
        print(f"Ngày {t+1}, {city}: vaccine phân phối = {dp[t+1][city][4]}")

# Số ca nhiễm cuối kỳ
print("Số ca nhiễm cuối kỳ:")
for city in cities:
    print(f"{city}: {dp[T][city][2]}")
