import base64


def add_packed():
    choice = int(input("Enter: \n- 1: Gold Bunny\n- 2: Pringles\n- 3: Macine\n- 4: Nutella\n"))

    if choice == 1:
        code = "4000539671203"
    elif choice == 2:
        code = "5053990167807"
    elif choice == 3:
        code = "8076809507110"
    elif choice == 4:
        code = "8000500415573"

    is_fridge = input("Is the product in the fridge? (yes/no) ")
    if is_fridge == "yes":
        is_fridge = "true"
    else:
        is_fridge = "false"

    quantity = int(input("Enter the quantity: "))

    expiration_date = input("Enter the expiration date: (yyyy-mm-dd) ")

    return code, is_fridge, quantity, expiration_date


def add_unpacked():
    name = int(input("Enter: \n- 1: Pera\n- 2: Mela\n- 3: Miele\n- 4: Taralli\n"))

    if name == 1:
        code = "pera"
    elif name == 2:
        code = "mela"
    elif name == 3:
        code = "miele"
    elif name == 4:
        code = "taralli"

    is_fridge = input("Is the product in the fridge? (yes/no) ")
    if is_fridge == "yes":
        is_fridge = "true"
    else:
        is_fridge = "false"

    quantity = int(input("Enter the quantity: "))

    return code, is_fridge, quantity


def add_guest():
    email = input("Enter the email: ")

    return email


def set_password():
    password = input("Enter the new password: ")

    return password


def create_user():
    username = input("Enter the username: ")
    password = input("Enter the password: ")

    return username, password


def delete_food():
    choice = int(input("Enter number to delete: \n- 1: Gold Bunny\n- 2: Mela\n- 3: Pera\n- 4: Nutella\n"))

    if choice == 1:
        name = "Gold Bunny"
    elif choice == 2:
        name = "Mela"
    elif choice == 3:
        name = "Pera"
    elif choice == 4:
        name = "Nutella"

    return name


def delete_guest():
    email = input("Enter the email of the guest to delete: ")

    return email


def food_to_search():
    name = input("Enter the name of the food to search: ")

    return name


def pantry_to_search():
    pantry = int(input("Enter the pantry to search: "))

    return pantry


def auth():
    username = input("Inserisci username: ")
    password = input("Inserisci password: ")

    string_to_encode = f"{username}:{password}"

    byte_string = string_to_encode.encode('utf-8')

    encoded_bytes = base64.b64encode(byte_string)

    encoded_string = encoded_bytes.decode('utf-8')

    return encoded_string
