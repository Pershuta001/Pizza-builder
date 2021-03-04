
% client(id, pib(Name,Surname, Patronym), date(dd,mm,yyyy), address(City, Street, House, Flat),
% Phone ).

client(1,name("Ivanov","Ivan","Ivanovych"), 12.12.1999,address("Kyiv","Maryny Tsvetaevoy", "14-Á", 521), ‘380648651235’).

% specialty(Specialty_id, Name)

specialty(145,"Prolog Developer").


% specialty_client(ClientId, Specialty_id)

specialty_client(1234567890,145).



% vacancy(Id, Position. Payment, Required_experience)



% fop_employer(Fop_id, Name_of_company,address(City, Street, House, Flat), phone(Phone_1,
% Phone_2, Phone_3), Email[ ], FOP_flag, FOP_code, FOP_name(Name,Surname, Patronym), FOP_birthday(dd,mm,yyyy))
employer(0123456789,"Arem&CO", "phone [1-3]").



% bussiness_employer(id, id_bussiness, Name_of_company, phone(Phone_1, Phone_2, Phone_3),
% Email[ ], Boss).

employer_address(0123456789,"Kyiv","Maryny Tsvetaevoy", "13", 13).

