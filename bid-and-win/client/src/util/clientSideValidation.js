
/**
 * validateClient function that takes in an client object with the following properties:
 *  firstName, lastName, dateOfBirth , gender, email, phone. And validate each property on the client side.
 * 
 * @param {Object} client
 * 
 * @returns true if all properties are valid, throw an error if any property is invalid.
*/

export function validateClient({ firstName, lastName, dateOfBirth, gender, email, phone }) {
    if (!firstName || firstName.length < 2) {
        throw new Error("First Name must be at least 2 characters long");
    }

    if (!lastName || lastName.length < 2) {
        throw new Error("Last Name must be at least 2 characters long");
    }

    if (!dateOfBirth) {
        throw new Error("Date of Birth is required");
    }

    // Assuming dateOfBirth is a Date object; you might need additional checks to validate format
    if (new Date(dateOfBirth).getFullYear() > new Date().getFullYear()) {
        throw new Error("Invalid Date of Birth");
    }

    if (!gender) {
        throw new Error("Gender is required");
    }

    if (!email) {
        throw new Error("Email is required");
    }

    if (!phone || phone.length < 10) {
        throw new Error("Phone must be at least 10 characters long");
    }

    return true;
}


export function validateAddress({ country, city, zip, street, houseOrApartmentNo }) {}