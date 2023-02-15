import React, { useEffect, useState } from "react";
import { useDispatch } from 'react-redux';
import { changeTitle } from "../../store/title/titleSlice";
import MultiStepInitializer from "components/MultiStepInitializer/MultiStepInitializer";

const Initializer = () => {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(changeTitle("Initializer"));
  });

  return <>
    <MultiStepInitializer />
  </>
}

export default Initializer;