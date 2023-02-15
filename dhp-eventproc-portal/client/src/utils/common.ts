import { notification } from "antd";

const getUserFirstAndLastNameCharacter = (name: string = "") => {
    let array = name.split(" ");
    return array[0].charAt(0) + array[array.length - 1].charAt(0);
}

const openNotificationWithIcon = (
    type: 'error' | 'success' | 'warning',
    description: string
) => {
    let message: 'Error' | 'Success' | 'Warning' | 'Infomation';
    let duration: number;
    switch (type) {
        case 'error':
            message = 'Error';
            duration = 10;
            break;
        case 'success':
            message = 'Success';
            duration = 4.5;
            break;
        case 'warning':
            message = 'Warning';
            duration = 4.5;
            break;
        default:
            message = "Infomation"
            duration = 4.5;
            break
    };

    notification[type]({
        message: message as string,
        description: description,
        duration: duration
    })
}

export {
    getUserFirstAndLastNameCharacter,
    openNotificationWithIcon,
}