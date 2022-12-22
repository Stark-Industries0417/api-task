package comento.apitask.configure;

public class CalcDegree {

    public String pm25Degree(Double num) {
        if (num >= 0 && num < 16) {
            return "좋음";
        } else if (num >= 16 && num < 36) {
            return "보통";
        } else if (num >= 36 && num < 76) {
            return "나쁨";
        } else {
            return "매우 나쁨";
        }
    }

    public String pm10Degree(Double num) {
        if (num >= 0 && num < 31) {
            return "좋음";
        } else if (num >= 31 && num < 81) {
            return "보통";
        } else if (num >= 81 && num < 151) {
            return "나쁨";
        } else {
            return "매우 나쁨";
        }
    }

    public String o3Degree(Double num) {
        if (num >= 0 && num < 0.031) {
            return "좋음";
        } else if (num >= 0.031 && num < 0.091) {
            return "보통";
        } else if (num >= 0.091 && num < 0.151) {
            return "나쁨";
        } else {
            return "매우 나쁨";
        }
    }

    public String no2Degree(Double num) {
        if (num >= 0 && num < 0.031) {
            return "좋음";
        } else if (num >= 0.031 && num < 0.061) {
            return "보통";
        } else if (num >= 0.061 && num < 0.201) {
            return "나쁨";
        } else {
            return "매우 나쁨";
        }
    }

    public String coDegree(Double num) {
        if (num >= 0 && num < 2.01) {
            return "좋음";
        } else if (num >= 2.01 && num < 9.01) {
            return "보통";
        } else if (num >= 9.01 && num < 15.01) {
            return "나쁨";
        } else {
            return "매우 나쁨";
        }
    }

    public String so2Degree(Double num) {
        if (num >= 0 && num < 0.021) {
            return "좋음";
        } else if (num >= 0.021 && num < 0.051) {
            return "보통";
        } else if (num >= 0.051 && num < 0.151) {
            return "나쁨";
        } else {
            return "매우 나쁨";
        }
    }
}
