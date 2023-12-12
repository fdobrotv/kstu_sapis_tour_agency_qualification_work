export const validateRequired = (value: string) => !!value.length;

export const validateRequiredNumber = (value: number) => !!value;

export const validateRequiredDate = (value: Date) => {
    if (value === null) {
        console.log("validateRequiredDate invalid");
        return false;
    }
    var now: Date = new Date();
    var target: Date = new Date(value);
    let inFuture: boolean = target.getDate() > now.getDate();
    console.log("inFuture: " + inFuture);
    return inFuture;
};